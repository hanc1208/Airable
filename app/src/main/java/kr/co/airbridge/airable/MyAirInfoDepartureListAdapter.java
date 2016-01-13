package kr.co.airbridge.airable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAirInfoDepartureListAdapter extends BaseAdapter{

    // 문자열을 보관 할 ArrayList
    private ArrayList<DepartureFlight> m_List;

    // 생성자
    public MyAirInfoDepartureListAdapter(ArrayList<DepartureFlight> m_List) {
        this.m_List=m_List;
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

        TextView        mtime    = null;
        TextView        mtimechange    = null;
        TextView        mcity    = null;
        TextView        mticketnum    = null;
        TextView        mgate    = null;
        TextView        mremark    = null;
        TextView        mcheck    = null;
        CustomHolder    holder  = null;

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_air_info_list_item, parent, false);

            mtime    = (TextView) convertView.findViewById(R.id.my_air_info_list_time);
            mtimechange    = (TextView) convertView.findViewById(R.id.my_air_info_list_time_change);
            mticketnum    = (TextView) convertView.findViewById(R.id.my_air_info_list_air_num);
            mremark    = (TextView) convertView.findViewById(R.id.my_air_info_list_remark);
            mcity    = (TextView) convertView.findViewById(R.id.my_air_info_list_city);
            mcheck    = (TextView) convertView.findViewById(R.id.my_air_info_list_check);
            mgate    = (TextView) convertView.findViewById(R.id.my_air_info_list_gate);

            // 홀더 생성 및 Tag로 등록
            holder = new CustomHolder();
            holder.mlist_remark   = mremark;
            holder.mlist_gate   = mgate;
            holder.mlist_check   = mcheck;
            holder.mlist_city   = mcity;
            holder.mlist_ticket_num   = mticketnum;
            holder.mlist_time   = mtime;
            holder.mlist_timechange   = mtimechange;
            convertView.setTag(holder);
        }
        else {
            holder  = (CustomHolder) convertView.getTag();
            mtime    = holder.mlist_time;
            mtimechange    = holder.mlist_timechange;
            mticketnum    = holder.mlist_ticket_num;
            mcity    = holder.mlist_city;
            mcheck    = holder.mlist_check;
            mgate = holder.mlist_gate;
            mremark=holder.mlist_remark;
        }

        // Text 등록
        mtime.setText(m_List.get(position).getScheduleDataTime());
        mtimechange.setText(m_List.get(position).getEstimatedDataTime());
        mcity.setText(m_List.get(position).getAirport());
        mticketnum.setText(m_List.get(position).getFlightId());
        mcheck.setText(m_List.get(position).getChkinrange());
        mgate.setText(m_List.get(position).getGatenumber());
        mremark.setText(m_List.get(position).getRemark());

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
        TextView    mlist_time;
        TextView    mlist_timechange;
        TextView    mlist_city;
        TextView    mlist_check;
        TextView    mlist_gate;
        TextView    mlist_ticket_num;
        TextView    mlist_remark;
    }

}
