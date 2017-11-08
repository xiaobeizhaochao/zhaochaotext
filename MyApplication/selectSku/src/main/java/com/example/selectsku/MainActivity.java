package com.example.selectsku;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.GridView;
import android.widget.TextView;

import com.example.selectsku.SkuAdapter.onItemClickListener;

/**
 * ������
 */
public class MainActivity extends Activity {

	List<SkuItme> mList;// sku����

	List<Bean> mColorList;// ��ɫ�б�
	List<Bean> mSizeList;// �����б�
	GridView gvColor;// ��ɫ
	GridView gvSize;// ����
	SkuAdapter skuColorAdapter;// ��ɫ������
	SkuAdapter skuSizeAdapter;// ����������
	String color;//
	String size;//
	TextView tvSkuName;// ��ʾsku
	TextView tvSkuStock;// ��ʾ���
	int stock = 0;// ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gvSize = (GridView) findViewById(R.id.gv_size);
		gvColor = (GridView) findViewById(R.id.gv_color);
		tvSkuName = (TextView) findViewById(R.id.tv_sku);
		tvSkuStock = (TextView) findViewById(R.id.tv_sku_stock);
		addData();
		stock = DataUtil.getAllStock(mList);
		if (stock > 0) {
			tvSkuStock.setText("��棺" + stock + "");
		}
		skuColorAdapter = new SkuAdapter(mColorList, this);
		gvColor.setAdapter(skuColorAdapter);
		skuColorAdapter.setItemClickListener(new onItemClickListener() {

			@Override
			public void onItemClick(Bean bean, int position) {
				// TODO Auto-generated method stub
				color = bean.getName();
				switch (bean.getStates()) {
				case "0":
					// ��ճ���
					mSizeList=DataUtil.clearAdapterStates(mSizeList);
					skuSizeAdapter.notifyDataSetChanged();
					// �����ɫ
					mColorList=DataUtil.clearAdapterStates(mColorList);
					skuColorAdapter.notifyDataSetChanged();
					color = "";
					// �ж�ʹ��ѡ���˳���
					if (!TextUtils.isEmpty(size)) {
						// ѡ�г��룬������
						stock =DataUtil.getSizeAllStock(mList,size);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						tvSkuName.setText("��ѡ�����");
						// ��ȡ�ó����Ӧ����ɫ�б�
						List<String> list = DataUtil.getColorListBySize(mList,size);
						if (list != null && list.size() > 0) {
							// ������ɫ�б�
							mColorList = DataUtil.setSizeOrColorListStates(mColorList,list, color);
							skuColorAdapter.notifyDataSetChanged();
						}
						mSizeList=DataUtil.setAdapterStates(mSizeList,size);
						skuSizeAdapter.notifyDataSetChanged();
					} else {
						// ���п��
						stock = DataUtil.getAllStock(mList);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						tvSkuName.setText("��ѡ�����,��ɫ����");
					}
					break;
				case "1":
					// ѡ����ɫ
					mColorList=DataUtil.updateAdapterStates(mColorList,"0", position);
					skuColorAdapter.notifyDataSetChanged();
					// �������ɫ��Ӧ�ĳ����б�
					List<String> list = DataUtil.getSizeListByColor(mList,color);
					if (!TextUtils.isEmpty(size)) {
						// �������ɫ������Ӧ�Ŀ��
						stock = DataUtil.getStockByColorAndSize(mList,color, size);
						tvSkuName.setText("���:" + color + " " + size);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						if (list != null && list.size() > 0) {
							// ���³����б�
							mSizeList = DataUtil.setSizeOrColorListStates(mSizeList,list, size);
							skuSizeAdapter.notifyDataSetChanged();
						}
					} else {
						// ������ɫ������
						stock = DataUtil.getColorAllStock(mList,color);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						tvSkuName.setText("��ѡ�����");
						if (list != null && list.size() > 0) {
							// ���³����б�
							mSizeList = DataUtil.setSizeOrColorListStates(mSizeList,list, "");
							skuSizeAdapter.notifyDataSetChanged();
						}
					}
					break;
				default:
					break;
				}
			}
		});

		skuSizeAdapter = new SkuAdapter(mSizeList, this);
		gvSize.setAdapter(skuSizeAdapter);
		skuSizeAdapter.setItemClickListener(new onItemClickListener() {

			@Override
			public void onItemClick(Bean bean, int position) {
				// TODO Auto-generated method stub
				size = bean.getName();
				switch (bean.getStates()) {
				case "0":
					// ��ճ���
					mSizeList=DataUtil.clearAdapterStates(mSizeList);
					skuSizeAdapter.notifyDataSetChanged();
					// �����ɫ
					mColorList=DataUtil.clearAdapterStates(mColorList);
					skuColorAdapter.notifyDataSetChanged();
					size = "";
					if (!TextUtils.isEmpty(color)) {
						// �������ɫ��Ӧ�����п��
						stock = DataUtil.getColorAllStock(mList,color);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						tvSkuName.setText("��ѡ�����");
						// �������ɫ��Ӧ�ĳ����б�
						List<String> list = DataUtil.getSizeListByColor(mList,color);
						if (list != null && list.size() > 0) {
							// ���³����б�
							mSizeList = DataUtil.setSizeOrColorListStates(mSizeList,list, size);
							skuSizeAdapter.notifyDataSetChanged();
						}
						mColorList=DataUtil.setAdapterStates(mColorList,color);
						skuColorAdapter.notifyDataSetChanged();
					} else {
						// ��ȡ���п��
						stock = DataUtil.getAllStock(mList);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						tvSkuName.setText("��ѡ�����,��ɫ����");
					}
					break;
				case "1":
					// ѡ�г���
					mSizeList=DataUtil.updateAdapterStates(mSizeList, "0", position);
					skuSizeAdapter.notifyDataSetChanged();
					// ��ȡ�ó����Ӧ����ɫ�б�
					List<String> list = DataUtil.getColorListBySize(mList,size);
					if (!TextUtils.isEmpty(color)) {
						// �������ɫ������Ӧ�Ŀ��
						stock = DataUtil.getStockByColorAndSize(mList,color, size);
						tvSkuName.setText("���:" + color + " " + size);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						if (list != null && list.size() > 0) {
							// ������ɫ�б�
							mColorList = DataUtil.setSizeOrColorListStates(mColorList,list, color);
							skuColorAdapter.notifyDataSetChanged();
						}
					} else {
						// ����ĳ�������п��
						stock = DataUtil.getSizeAllStock(mList,size);
						if (stock > 0) {
							tvSkuStock.setText("��棺" + stock + "");
						}
						tvSkuName.setText("��ѡ����ɫ����");
						if (list != null && list.size() > 0) {
							mColorList =  DataUtil.setSizeOrColorListStates(mColorList,list, "");
							skuColorAdapter.notifyDataSetChanged();
						}
					}
					break;
				default:
					break;
				}
			}
		});
	}



	
	/**
	 * ģ������
	 */
	private void addData() {
		mList = new ArrayList<SkuItme>();
		mColorList = new ArrayList<Bean>();
		mSizeList = new ArrayList<Bean>();
		String[] colorArr = Constants.colorArr;
		String[] sizeArr = Constants.sizeArr;
		int color = colorArr.length;
		int size = sizeArr.length;

		for (int i = 0; i < color; i++) {
			Bean bean = new Bean();
			bean.setName(colorArr[i]);
			bean.setStates("1");
			mColorList.add(bean);
		}
		for (int i = 0; i < size; i++) {
			Bean bean = new Bean();
			bean.setName(sizeArr[i]);
			bean.setStates("1");
			mSizeList.add(bean);
		}

		String color0 = colorArr[0];
		String size0 = sizeArr[0];
		String color1 = colorArr[1];
		String size1 = sizeArr[1];
		String color2 = colorArr[2];
		String size2 = sizeArr[2];
		String color3 = colorArr[3];
		String size3 = sizeArr[3];
		String color4 = colorArr[4];
		String size4 = sizeArr[4];
		String size5 = sizeArr[5];
		SkuItme item0 = new SkuItme();
		item0.setId("1");
		item0.setSkuColor(color0);
		item0.setSkuSize(size0);
		item0.setSkuStock(10);
		mList.add(item0);
		SkuItme item1 = new SkuItme();
		item1.setId("2");
		item1.setSkuColor(color0);
		item1.setSkuSize(size1);
		item1.setSkuStock(1);
		mList.add(item1);
		SkuItme item2 = new SkuItme();
		item2.setId("3");
		item2.setSkuColor(color1);
		item2.setSkuSize(size0);
		item2.setSkuStock(12);
		mList.add(item2);
		SkuItme item3 = new SkuItme();
		item3.setId("4");
		item3.setSkuColor(color1);
		item3.setSkuSize(size2);
		item3.setSkuStock(123);
		mList.add(item3);
		SkuItme item4 = new SkuItme();
		item4.setId("5");
		item4.setSkuColor(color1);
		item4.setSkuSize(size1);
		item4.setSkuStock(53);
		mList.add(item4);
		SkuItme item5 = new SkuItme();
		item5.setId("6");
		item5.setSkuColor(color2);
		item5.setSkuSize(size1);
		item5.setSkuStock(13);
		mList.add(item5);
		SkuItme item6 = new SkuItme();
		item6.setId("7");
		item6.setSkuColor(color0);
		item6.setSkuSize(size3);
		item6.setSkuStock(18);
		mList.add(item6);
		SkuItme item7 = new SkuItme();
		item7.setId("8");
		item7.setSkuColor(color2);
		item7.setSkuSize(size3);
		item7.setSkuStock(14);
		mList.add(item7);
		SkuItme item8 = new SkuItme();
		item8.setId("9");
		item8.setSkuColor(color1);
		item8.setSkuSize(size3);
		item8.setSkuStock(22);
		mList.add(item8);
		SkuItme item9 = new SkuItme();
		item9.setId("10");
		item9.setSkuColor(color0);
		item9.setSkuSize(size4);
		item9.setSkuStock(29);
		mList.add(item9);
		SkuItme item10 = new SkuItme();
		item10.setId("11");
		item10.setSkuColor(color2);
		item10.setSkuSize(size5);
		item10.setSkuStock(64);
		mList.add(item10);
		SkuItme item11 = new SkuItme();
		item11.setId("12");
		item11.setSkuColor(color3);
		item11.setSkuSize(size2);
		item11.setSkuStock(70);
		mList.add(item11);
		SkuItme item12 = new SkuItme();
		item12.setId("13");
		item12.setSkuColor(color4);
		item12.setSkuSize(size0);
		item12.setSkuStock(80);
		mList.add(item12);
		SkuItme item13 = new SkuItme();
		item13.setId("14");
		item13.setSkuColor(color3);
		item13.setSkuSize(size4);
		item13.setSkuStock(35);
		mList.add(item13);
		SkuItme item14 = new SkuItme();
		item14.setId("15");
		item14.setSkuColor(color4);
		item14.setSkuSize(size1);
		item14.setSkuStock(62);
		mList.add(item14);
		SkuItme item15 = new SkuItme();
		item15.setId("16");
		item15.setSkuColor(color3);
		item15.setSkuSize(size5);
		item15.setSkuStock(41);
		mList.add(item15);
		SkuItme item16 = new SkuItme();
		item16.setId("17");
		item16.setSkuColor(color1);
		item16.setSkuSize(size5);
		item16.setSkuStock(39);
		mList.add(item16);
		SkuItme item17 = new SkuItme();
		item17.setId("18");
		item17.setSkuColor(color4);
		item17.setSkuSize(size5);
		item17.setSkuStock(37);
		mList.add(item17);
		SkuItme item18 = new SkuItme();
		item18.setId("19");
		item18.setSkuColor(color4);
		item18.setSkuSize(size2);
		item18.setSkuStock(44);
		mList.add(item18);
		SkuItme item19 = new SkuItme();
		item19.setId("20");
		item19.setSkuColor(color4);
		item19.setSkuSize(size3);
		item19.setSkuStock(61);
		mList.add(item19);
	}
}
