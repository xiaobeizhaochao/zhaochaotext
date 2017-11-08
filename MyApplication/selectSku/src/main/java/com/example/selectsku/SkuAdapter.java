package com.example.selectsku;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * skuѡ��
 */
public class SkuAdapter extends BaseAdapter {
	private List<Bean> list;//����Դ
	private LayoutInflater mInflater;// �õ�һ��LayoutInfalter�����������벼��
	public onItemClickListener itemClickListener;// �ӿڻص�

	public void setItemClickListener(onItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public SkuAdapter(List<Bean> list, Context context) {
		super();
		this.mInflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.gridview_item, null);
			/** �õ������ؼ��Ķ��� */
			holder.title = (TextView) convertView.findViewById(R.id.ItemText);
			holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
			convertView.setTag(holder);// ��ViewHolder����
		} else {
			holder = (ViewHolder) convertView.getTag();// ȡ��ViewHolder����
		}
		final Bean bean = list.get(position);
	
		switch (bean.getStates()) {
		// ѡ��
		case "0":
			holder.layout.setBackgroundResource(R.xml.shape2);
			holder.title.setTextColor(Color.WHITE);
			break;
		// δѡ��
		case "1":
			holder.layout.setBackgroundResource(R.xml.shape1);
			holder.title.setTextColor(Color.BLACK);
			break;
		// ����ѡ
		case "2":
			holder.layout.setBackgroundResource(R.xml.shape1);
			holder.title.setTextColor(Color.parseColor("#999999"));
			break;
		default:
			break;
		}
		/** ����TextView��ʾ�����ݣ������Ǵ���ڶ�̬�����е����� */
		holder.title.setText(bean.getName());
		holder.layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (itemClickListener != null) {
					if(!bean.getStates().equals("2")){
						itemClickListener.onItemClick(bean, position);
					}
				}
			}
		});
		return convertView;
	}

	public final class ViewHolder {
		public TextView title;
		public LinearLayout layout;
	}

	public interface onItemClickListener {
		public void onItemClick(Bean bean, int position);
	}
}
