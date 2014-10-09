package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.database.BusituDatabaseHelper;
import android.Manifest.permission;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavegarActivity extends Fragment {
	
	TextView tvNavegar;
	TextView tvPesquisar;
	EditText etPesquisar;
	Button btnPesquisar;
	
	LinearLayout llNavegar;
	LinearLayout llPesquisar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
			
		View rootView = inflater.inflate(R.layout.fragment_navegar, container, false);
		tvNavegar = (TextView) rootView.findViewById(R.id.labelNavegar);
		tvPesquisar = (TextView) rootView.findViewById(R.id.labelPesquisar);
		llNavegar = (LinearLayout) rootView.findViewById(R.id.llRota);
		llPesquisar = (LinearLayout) rootView.findViewById(R.id.llPesquisar);
		
		etPesquisar = (EditText) rootView.findViewById(R.id.textPesquisar);
		
		btnPesquisar = (Button) rootView.findViewById(R.id.btnPesquisar);
		
		llNavegar.setVisibility(View.GONE);
		llPesquisar.setVisibility(View.GONE);
		
		btnPesquisar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String item = etPesquisar.getText().toString();
				   SQLiteDatabase db = BusituDatabaseHelper.getInstance(getActivity()).getReadableDatabase();
				   
				   String query = ""
				   		+ "SELECT "
				   		+ 		"linha.numero_onibus, linha.nome "
				   		+ "FROM linha "
				   		+ "INNER JOIN percurso "
				   		+ "ON linha._id = percurso.id_linha "
				   		+ "AND percurso.rota LIKE ?";
				   
				Cursor cursor =   db.rawQuery(query, new String[]{"%"+item+"%"});
				   Log.d("query", cursor.getString(0));
				
			}
		});
		
		
		tvNavegar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(llNavegar.getVisibility() == View.VISIBLE){
					collapse(llNavegar);
				}else{
					expand(llNavegar);
				}
								
				
			}
		});
		
		tvPesquisar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(llPesquisar.getVisibility() == View.VISIBLE){
					collapse(llPesquisar);
				}else{
					expand(llPesquisar);
				}
			}
		});
		
		return rootView;
		
	}
	
	
	public static void expand(final View v) {
	    v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    final int targtetHeight = v.getMeasuredHeight();

	    v.getLayoutParams().height = 0;
	    v.setVisibility(View.VISIBLE);
	    Animation a = new Animation()
	    {
	        @Override
	        protected void applyTransformation(float interpolatedTime, Transformation t) {
	            v.getLayoutParams().height = interpolatedTime == 1
	                    ? LayoutParams.WRAP_CONTENT
	                    : (int)(targtetHeight * interpolatedTime);
	            v.requestLayout();
	        }

	        @Override
	        public boolean willChangeBounds() {
	            return true;
	        }
	    };

	    // 1dp/ms
	    a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
	    v.startAnimation(a);
	}

	public static void collapse(final View v) {
	    final int initialHeight = v.getMeasuredHeight();

	    Animation a = new Animation()
	    {
	        @Override
	        protected void applyTransformation(float interpolatedTime, Transformation t) {
	            if(interpolatedTime == 1){
	                v.setVisibility(View.GONE);
	            }else{
	                v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
	                v.requestLayout();
	            }
	        }

	        @Override
	        public boolean willChangeBounds() {
	            return true;
	        }
	    };

	    // 1dp/ms
	    a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
	    v.startAnimation(a);
	}
	

}
