package kr.co.airbridge.airable;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 미령 on 2016-01-16.
 */
public class DepartureCityDialogAdapter extends BaseAdapter {


    // 문자열을 보관 할 ArrayList
    private ArrayList<AirportCodeModel> m_List;

    // 생성자
    public DepartureCityDialogAdapter(ArrayList<AirportCodeModel> m_List) {
        this.m_List=m_List;
        // m_List = new ArrayList<AirListDepartingFlight>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        TextView departure_city_name    = null;
        TextView departure_city_airport_code    = null;
        CustomHolder    holder  = null;

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.departure_airport_dialog_list_item, parent, false);

            departure_city_name    = (TextView) convertView.findViewById(R.id.departure_city_list_name);
            departure_city_airport_code    = (TextView) convertView.findViewById(R.id.departure_city_airport_code);


            // 홀더 생성 및 Tag로 등록
            holder = new CustomHolder();
            holder.departure_city_list_name   = departure_city_name;
            holder.departure_city_airport_code   = departure_city_airport_code;
            convertView.setTag(holder);
        }
        else {
            holder  = (CustomHolder) convertView.getTag();
            departure_city_name    = holder.departure_city_list_name;
            departure_city_airport_code    = holder.departure_city_airport_code;
        }


        // Text 등록
        departure_city_name.setText(m_List.get(position).getAirport());
        departure_city_airport_code.setText(m_List.get(position).getFlightId());


        /*
        // 리스트 아이템을 터치 했을 때 이벤트 발생
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        */

        return convertView;
    }

    private class CustomHolder {
        TextView    departure_city_list_name;
        TextView    departure_city_airport_code;
    }


}