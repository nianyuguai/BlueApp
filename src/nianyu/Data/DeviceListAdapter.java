package nianyu.Data;

import java.util.List;
import java.util.Map;

import nianyu.blueapp.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DeviceListAdapter extends BaseAdapter{
	private LayoutInflater listContainer;//视图容器
	private Context context;
	private List<Map<String, Object>> deviceItems;
	private static boolean D = true;
	private String TAG = "DeviceListAdapter";
	
	/*构造函数*/
	public DeviceListAdapter(Context context,List<Map<String, Object>> items) {
	    this.context = context;
		this.listContainer = LayoutInflater.from(context);
		this.deviceItems = items;
	}
	public final class DeviceListView{
		public TextView name_tv;
		public TextView address_tv;
		public TextView pair_tv;
		public TextView assi_tv;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return deviceItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View cv, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(D)Log.d(TAG, "getView");
		
		DeviceListView lv = null;
		if(cv==null){
			lv = new DeviceListView();
			cv = listContainer.inflate(R.layout.device_item, null);
			lv.name_tv = (TextView)
		
		}
		return null;
	}

}
