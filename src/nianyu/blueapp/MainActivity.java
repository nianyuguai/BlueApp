package nianyu.blueapp;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import nianyu.Bluetooth.BtDevice;
import nianyu.Data.DeviceListAdapter;
import android.app.ActionBar;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {
	//静态常量
	private boolean D = true;
	private String TAG = "BlutApp";
	
	//控件
	private Button mSearchBtn = null;
	private ProgressBar mSearchPb;
	private TextView mStatusTv = null;
	
	
	//蓝牙
	private BtDevice mBtDevice;
	private BluetoothAdapter mAdapter;
	//private ArrayAdapter<String> mDeviceArrayAdapter;
	//private ArrayAdapter<String> mConversationArrayAdapter;
	private DeviceListAdapter mDeviceAdapter;
	private List<Map<String, Object>> deviceItems;
	private ListView mConversationView;
	private ListView device_lv;
	
	//数据
	private String[] mLocations;
	private SharedPreferences sp;
	
	//标志位
	private boolean isQuit = false; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if(D)Log.i(TAG,"--onCreate()---");
		mLocations = getResources().getStringArray(R.array.locations);
		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.locations, R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
		
		//设置默认的设备类型
		sp = getSharedPreferences(TAG, MODE_PRIVATE);
		int device_type = sp.getInt("device_type", 0);
		mBtDevice = new BtDevice(null,null,device_type);
		
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setListNavigationCallbacks(list, new OnNavigationListener(){

			@Override
			public boolean onNavigationItemSelected(int itemPosition,
					long itemId) {
				if(D)Log.d(TAG,mLocations[itemPosition]);
				mBtDevice.setType(itemPosition);
				Editor edit = sp.edit();
				edit.putInt("device_type", itemPosition);
				edit.commit();
				return true;		
			}
			
		});
		getSupportActionBar().setSelectedNavigationItem(device_type);
		
		//控件设置
		mSearchPb = (ProgressBar)findViewById(R.id.search_pb);
		mStatusTv = (TextView)findViewById(R.id.bt_status);
		mSearchBtn = (Button)findViewById(R.id.search_btn);
		mSearchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}	
		});
		
		//蓝牙对象初始化
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		//设备listview
		mDeviceAdapter = new DeviceListAdapter(this,deviceItems);
		device_lv = (ListView) findViewById(R.id.device_lv);
		device_lv.setAdapter(mDeviceAdapter);
		
		//mDeviceArrayAdapter = new ArrayAdapter<String>(getView().getContext(),R.layout.device_name);		
		//device_lv = (ListView) findViewById(R.id.device_lv);
		//device_lv.setAdapter(mDeviceArrayAdapter);
		//device_lv.setOnItemClickListener(mDeviceClickListener);
		//device_lv.setOnCreateContextMenuListener(mCCMListener);

	}
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(D)Log.i(TAG,"--onStart()---");
		
		if(!mAdapter.isEnabled()){
			mSearchBtn.setClickable(false);
			mStatusTv.setText("蓝牙未开启");
		 }
	}


	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	Timer timer = new Timer();
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(isQuit == false){
				isQuit = true;
				Toast.makeText(getBaseContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
				TimerTask m_task = null;
				m_task = new TimerTask(){

					@Override
					public void run() {
						isQuit = false;
					}
					
				};
				timer.schedule(m_task, 2000);
			}else{
				MainActivity.this.finish();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	} 
	
	
	
	


}
