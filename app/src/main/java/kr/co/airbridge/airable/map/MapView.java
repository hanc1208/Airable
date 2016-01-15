package kr.co.airbridge.airable.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

import kr.co.airbridge.airable.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MapView extends ImageView {
    private Map map;
    private SVG svg;
    private PhotoViewAttacher photoViewAttacher;

    private Point currentPosition;
    private Path path;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.photoViewAttacher = new PhotoViewAttacher(this);
        setScaleType(ScaleType.FIT_CENTER);
    }

    public void setMap(final Map map) throws SVGParseException, IOException {
        this.map = map;
        svg = SVG.getFromAsset(getContext().getAssets(), "map.svg");
        setImageDrawable(new MapDrawable());
        photoViewAttacher.update();
    }

    public Map getMap() {
        return map;
    }

    public void setCurrentPosition(int x, int y) {
        setCurrentPosition(new Point(x, y));
    }

    public void setCurrentPosition(Point point) {
        currentPosition = new Point(point);
        postInvalidate();
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    class MapDrawable extends Drawable {
        private RectF rect = new RectF();
        private Drawable current = ContextCompat.getDrawable(getContext(), R.drawable.map_current);

        @Override
        public void draw(Canvas canvas) {
            canvas.save();
            rect.set(0, 0, map.getWidth(), map.getHeight());
            svg.renderToCanvas(canvas, rect);
            canvas.restore();

            if (path != null) {
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(path.getLineColor());
                paint.setStrokeWidth(path.getLineWidth());

                android.graphics.Path canvasPath = new android.graphics.Path();
                for (Vertex vertex : path.getVertexes()) {
                    if (canvasPath.isEmpty()) {
                        canvasPath.moveTo(vertex.getX(), vertex.getY());
                    } else {
                        canvasPath.lineTo(vertex.getX(), vertex.getY());
                    }
                }

                canvas.drawPath(canvasPath, paint);

                for (int i = 0; i < path.getMarkerMap().size(); i++) {
                    int vertexId = path.getMarkerMap().keyAt(i);
                    for (Vertex vertex : path.getVertexes()) {
                        if (vertex.getId() == vertexId) {
                            drawDrawable(canvas, path.getMarkerMap().get(vertexId), vertex.getX(), vertex.getY());
                        }
                    }
                }
            }

            if (currentPosition != null) {
                drawDrawable(canvas, current, currentPosition.x, currentPosition.y);
            }
        }

        private void drawDrawable(Canvas canvas, Drawable drawable, int x, int y) {
            canvas.save();
            canvas.translate(x, y);
            canvas.translate(-drawable.getIntrinsicWidth() / 2, -drawable.getIntrinsicHeight() / 2);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            canvas.restore();
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 1;
        }

        @Override
        public int getIntrinsicWidth() {
            return map.getWidth();
        }

        @Override
        public int getIntrinsicHeight() {
            return map.getHeight();
        }
    };
}
