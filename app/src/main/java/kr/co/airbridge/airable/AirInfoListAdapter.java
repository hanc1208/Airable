package kr.co.airbridge.airable;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;




public class AirInfoListAdapter extends BaseAdapter  {


    // 문자열을 보관 할 ArrayList
    private ArrayList<DepartingFlight>   m_List;

    // 생성자
    public AirInfoListAdapter(ArrayList<DepartingFlight> m_List) {
        this.m_List=m_List;
       // m_List = new ArrayList<DepartingFlight>();
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

        TextView        time    = null;
        TextView        timechange    = null;
        TextView        city    = null;
        TextView        ticketnum    = null;
        TextView        airport    = null;
        ImageView       btn     = null;
        CustomHolder    holder  = null;

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.air_info_list_item, parent, false);

            time    = (TextView) convertView.findViewById(R.id.air_info_list_time);
            timechange    = (TextView) convertView.findViewById(R.id.air_info_list_time_change);
            ticketnum    = (TextView) convertView.findViewById(R.id.air_info_list_air_num);
            airport    = (TextView) convertView.findViewById(R.id.air_info_list_airport);
            city    = (TextView) convertView.findViewById(R.id.air_info_list_city);
            btn     = (ImageView) convertView.findViewById(R.id.air_info_list_button);





            // 홀더 생성 및 Tag로 등록
            holder = new CustomHolder();
            holder.list_airport   = airport;
            holder.list_city   = city;
            holder.list_ticket_num   = ticketnum;
            holder.list_time   = time;
            holder.list_timechange   = timechange;
            holder.list_button        = btn;
            convertView.setTag(holder);
        }
        else {
            holder  = (CustomHolder) convertView.getTag();
            time    = holder.list_time;
            timechange    = holder.list_timechange;
            ticketnum    = holder.list_ticket_num;
            city    = holder.list_city;
            airport    = holder.list_airport;
            btn     = holder.list_button;
        }


        // Text 등록
        time.setText(m_List.get(position).getScheduleDataTime());
        timechange.setText(m_List.get(position).getEstimatedDataTime());
        city.setText(m_List.get(position).getAirport());
        ticketnum.setText(m_List.get(position).getFlightId());
        airport.setText(m_List.get(position).getAirline());


        if(timechange.getText().toString().equals("")){
            View viewStroke = convertView.findViewById(R.id.air_info_list_stroke);
            viewStroke.setBackgroundColor(Color.parseColor("#00000000"));
            viewStroke.setVisibility(View.INVISIBLE);
        }

        /*
        // 버튼 이벤트 등록
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        TextView    list_time;
        TextView    list_timechange;
        TextView    list_city;
        TextView    list_airport;
        TextView    list_ticket_num;
        ImageView   list_button;
    }


}