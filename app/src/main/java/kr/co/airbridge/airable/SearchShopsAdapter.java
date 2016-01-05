package kr.co.airbridge.airable;

import android.content.Context;
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
        CustomHolder holder = null;

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
            holder = new CustomHolder();
            holder.h_shopImage = shopImage;
            holder.h_shopLocation = shopLocation;
            holder.h_shopTitle = shopTitle;
            convertView.setTag(holder);

            // 리스트의 Shop 아이템을 선택했을 때 아래의 이벤트가 발생
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Item click event : "+shopList.get(pos).getShopTitle(), Toast.LENGTH_SHORT).show(); // Test
                }
            });
        }else{
            holder = (CustomHolder) convertView.getTag();
            shopTitle = holder.h_shopTitle;
            shopLocation = holder.h_shopLocation;
            shopImage = holder.h_shopImage;
        }

        shopTitle.setText(shopList.get(pos).getShopTitle());
        shopLocation.setText(shopList.get(pos).getShopLocation());
        shopImage.setImageResource(shopList.get(pos).getShopImage());

        return convertView;
    }

    public void addItem(String title, String location, int image){
        Shop shop = new Shop(title, location, image);
        shopList.add(shop);
    }

    public void clearList(){
        shopList.clear();
    }
}

class CustomHolder{
    TextView h_shopTitle;
    TextView h_shopLocation;
    ImageView h_shopImage;
}

class Shop{
    private String shopTitle;
    private String shopLocation;
    private int shopImage;

    public Shop(String title, String location, int image){
        shopTitle = title;
        shopLocation = location;
        shopImage = image;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }
}