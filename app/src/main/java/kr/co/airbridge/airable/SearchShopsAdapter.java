package kr.co.airbridge.airable;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchShopsAdapter extends BaseAdapter {
    private ArrayList<Shop> shopList;

    public SearchShopsAdapter(){
        shopList = new ArrayList<Shop>();
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        try{
            return shopList.get(position);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        TextView shopTitle=null;
        TextView shopLocation=null;
        ImageView shopImage=null;
        SearchShopsCustomHolder holder = null;

        if(convertView == null){
            // View가 null일 경우 searchshops_item 레이아웃 사용
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.searchshops_item, parent, false);

            // TextView 받아오기
            shopTitle = (TextView) convertView.findViewById(R.id.searchshops_item_title);
            shopLocation = (TextView) convertView.findViewById(R.id.searchshops_item_location);
            // ImageView 받아오기
            shopImage = (ImageView) convertView.findViewById(R.id.searchshops_item_image);

            // Holder 생성 및 등록
            holder = new SearchShopsCustomHolder();
            holder.h_shopImage = shopImage;
            holder.h_shopLocation = shopLocation;
            holder.h_shopTitle = shopTitle;
            convertView.setTag(holder);

            // 리스트의 Shop 아이템을 선택했을 때 아래의 이벤트가 발생
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), ShopDetailActivity.class);
                    i.putExtra("shopTitle", shopList.get(pos).getEntrpskoreannm());
                    i.putExtra("shopLocation", shopList.get(pos).getLckoreannm());
                    i.putExtra("shopTime", shopList.get(pos).getServicetime());
                    i.putExtra("shopInfo", "저희 매장은 소화제, 진통제 등 여행의 필수품인 구급약을 두루 갖추고 있습니다.\n행복한 여행을 위한 준비, 저희 매장에서 하세요!"); // Default info
                    i.putExtra("shopTel", shopList.get(pos).getTel());
                    v.getContext().startActivity(i);
                }
            });
        }else{
            holder = (SearchShopsCustomHolder) convertView.getTag();
            shopTitle = holder.h_shopTitle;
            shopLocation = holder.h_shopLocation;
            shopImage = holder.h_shopImage;
        }

        shopTitle.setText(shopList.get(pos).getEntrpskoreannm());
        shopLocation.setText(shopList.get(pos).getLckoreannm());
        shopImage.setImageResource(R.drawable.user_image_default);

        return convertView;
    }

    public void addItem(Shop shop){ shopList.add(shop); }
    public void clearList(){
        shopList.clear();
    }
}

class SearchShopsCustomHolder{
    TextView h_shopTitle;
    TextView h_shopLocation;
    ImageView h_shopImage;
}