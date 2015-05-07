package com.iyoutingche.android.utils;



import com.zhy.weixin6.ui.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {

	public static void showToast(Context context,String str) {
		Toast toast=Toast.makeText(context, null, Toast.LENGTH_SHORT);
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=inflater.inflate(R.layout.toast,new LinearLayout(context),false);
		TextView text=(TextView) v.findViewById(R.id.toast_text);
		text.setText(str);
		toast.setView(v);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
