package br.com.tcc.busitu.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.HorarioBean;
import br.com.tcc.busitu.model.HorarioDAO;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.PercursoBean;
import br.com.tcc.busitu.util.Util;

public class LinhasDetail extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linhas_detail);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
////		getMenuInflater().inflate(R.menu.linhas_detail, menu);
////		return true;
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener{

		//public static final String[] PROJECTION = { "_id", "nome", "rota", "regiao_atendida", "tempo", "id_linha"};
		public static final String[] PROJECTION = {PercursoBean.COL_NOME, PercursoBean.COL_REGIAO_ATENDIDA, PercursoBean.COL_ROTA,
			PercursoBean.COL_TEMPO_PERCURSO };
		
		TextView txtHorarios, txtHorarioDiasUteis, txtHorarioSabados, txtHorarioDomingosFeriados,
		txtRotaInicialNome, txtRotaInicialRegiaoAtendida, txtRotaInicialRegiaoAtendidaTexto,
		txtRotaInicialItinerario, txtRotaInicialItinerarioTexto, txtRotaInicialTempoPercurso, 
		txtRotaInicialTempoPercursoTexto, txtRotaInicialFinal, txtRotaFinalRegiaoAtendida, 
		txtRotaFinalRegiaoAtendidaTexto, txtRotaFinalItinerario, txtRotaFinalItinerarioTexto,
		txtRotaFinalTempoPercurso, txtRotaFinalTempoPercursoTexto;
		
		LinearLayout llHorarios, llRotaInicial, llRotaFinal;
		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_linhas_detail,
					container, false);
			
			Intent i = getActivity().getIntent();
			Bundle b = i.getExtras();
			
			PercursoBean percursoBean = (PercursoBean) b.getSerializable("PercursoBean");
			long id = 0;
			
			Cursor mCursor = percursoBean.getResultado();
			

			
			txtRotaInicialNome 					= (TextView) rootView.findViewById(R.id.txtRotaInicialNome);
			txtRotaInicialRegiaoAtendida 		= (TextView) rootView.findViewById(R.id.txtRotaInicialRegiaoAtendida);
			txtRotaInicialRegiaoAtendidaTexto 	= (TextView) rootView.findViewById(R.id.txtRotaInicialRegiaoAtendidaTexto);
			txtRotaInicialItinerario 			= (TextView) rootView.findViewById(R.id.txtRotaInicialItinerario);
			txtRotaInicialItinerarioTexto 		= (TextView) rootView.findViewById(R.id.txtRotaInicialItinerarioTexto);
			txtRotaInicialTempoPercurso 		= (TextView) rootView.findViewById(R.id.txtRotaInicialTempoPercurso);
			txtRotaInicialTempoPercursoTexto 	= (TextView) rootView.findViewById(R.id.txtRotaInicialTempoPercursoTexto);
			
			txtRotaInicialFinal 				= (TextView) rootView.findViewById(R.id.txtRotaInicialFinal);
			txtRotaFinalRegiaoAtendida 			= (TextView) rootView.findViewById(R.id.txtRotaFinalRegiaoAtendida);
			txtRotaFinalRegiaoAtendidaTexto 	= (TextView) rootView.findViewById(R.id.txtRotaFinalRegiaoAtendidaTexto);
			txtRotaFinalItinerario 				= (TextView) rootView.findViewById(R.id.txtRotaFinalItinerario);
			txtRotaFinalItinerarioTexto 		= (TextView) rootView.findViewById(R.id.txtRotaFinalItinerarioTexto);
			txtRotaFinalTempoPercurso 			= (TextView) rootView.findViewById(R.id.txtRotaFinalTempoPercurso);
			txtRotaFinalTempoPercursoTexto 		= (TextView) rootView.findViewById(R.id.txtRotaFinalTempoPercursoTexto);
			
			txtHorarios							= (TextView) rootView.findViewById(R.id.txtHorarios);
			txtHorarioDiasUteis					= (TextView) rootView.findViewById(R.id.txtHorarioDiasUteis);
			txtHorarioDomingosFeriados			= (TextView) rootView.findViewById(R.id.txtHorarioDomingosFeriados);
			txtHorarioSabados					= (TextView) rootView.findViewById(R.id.txtHorarioSabados);
			
			llRotaInicial						= (LinearLayout) rootView.findViewById(R.id.llRotaInicial);
			llHorarios							= (LinearLayout) rootView.findViewById(R.id.llHorarios);
			
			txtRotaInicialNome.setOnClickListener(this);
			txtRotaInicialRegiaoAtendida.setOnClickListener(this);
			txtRotaInicialItinerario.setOnClickListener(this);
			txtRotaInicialTempoPercurso.setOnClickListener(this);
			txtRotaInicialFinal.setOnClickListener(this);
			txtRotaFinalRegiaoAtendida.setOnClickListener(this);
			txtRotaFinalItinerario.setOnClickListener(this);
			txtRotaFinalTempoPercurso.setOnClickListener(this);
			txtHorarios.setOnClickListener(this);
			
			if(mCursor.moveToFirst()){
				
				id = mCursor.getLong(mCursor.getColumnIndex(PercursoBean.COL_ID_LINHA));
				
				
				do{
					if(mCursor.isFirst()){
						txtRotaInicialNome
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_NOME)));
						txtRotaInicialRegiaoAtendidaTexto
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_REGIAO_ATENDIDA)));
						txtRotaInicialItinerarioTexto
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_ROTA)));
						txtRotaInicialTempoPercursoTexto
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_TEMPO_PERCURSO)) + " minutos");
					}
					else if(mCursor.isLast()){
						txtRotaInicialFinal
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_NOME)));
						txtRotaFinalRegiaoAtendidaTexto
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_REGIAO_ATENDIDA)));
						txtRotaFinalItinerarioTexto
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_ROTA)));
						txtRotaFinalTempoPercursoTexto
						.setText(mCursor.getString(mCursor.getColumnIndex(PercursoBean.COL_TEMPO_PERCURSO)) + " minutos");
					}
				}while(mCursor.moveToNext());
			}
			
			
			HorarioDAO horarioDAO = new HorarioDAO(getActivity().getApplicationContext());
			HorarioBean horarioBean = horarioDAO.buscaHorarioPorLinha(id);
			Cursor hCursor = horarioBean.getmCursor();
			
			if(hCursor.moveToFirst()){
				do{
					if(hCursor.getInt(3) == 1){
						txtHorarioDiasUteis
						.setText(hCursor.getString(1) + " - "
								+ hCursor.getString(2));
					}
					else if(hCursor.getInt(3) == 2){
						txtHorarioSabados
						.setText(hCursor.getString(1) + " - "
								+ hCursor.getString(2));
					}
					else{
						txtHorarioDomingosFeriados
						.setText(hCursor.getString(1) + " - "
								+ hCursor.getString(2));
						
					}
					
				}while(hCursor.moveToNext());
			}
			
			
			
			
			
			return rootView;
		}

		@Override
		public void onClick(View v) {
			
			switch(v.getId()){
			
			case R.id.txtRotaInicialRegiaoAtendida:
				
				if(txtRotaInicialRegiaoAtendidaTexto.getVisibility() == View.VISIBLE){
					txtRotaInicialRegiaoAtendida.setText("+ REGIÃO ATENDIDA");
					Util.collapse(txtRotaInicialRegiaoAtendidaTexto);
				}
				else{
					txtRotaInicialRegiaoAtendida.setText("- REGIÃO ATENDIDA");
					Util.expand(txtRotaInicialRegiaoAtendidaTexto);
				}
				break;
				
			case R.id.txtRotaInicialItinerario:		
				if(txtRotaInicialItinerarioTexto.getVisibility() == View.VISIBLE){
					txtRotaInicialItinerario.setText("+ ITINERÁRIO");
					Util.collapse(txtRotaInicialItinerarioTexto);
				}
				else{
					txtRotaInicialItinerario.setText("- ITINERÁRIO");
					Util.expand(txtRotaInicialItinerarioTexto);
				}
				break;
			case R.id.txtRotaInicialTempoPercurso:
				if(txtRotaInicialTempoPercursoTexto.getVisibility() == View.VISIBLE){
					txtRotaInicialTempoPercurso.setText("+ TEMPO DE PERCURSO");
					Util.collapse(txtRotaInicialTempoPercursoTexto);
				}
				else{
					txtRotaInicialTempoPercurso.setText("- TEMPO DE PERCURSO");
					Util.expand(txtRotaInicialTempoPercursoTexto);
				}
				break;
			case R.id.txtRotaFinalRegiaoAtendida:
				if(txtRotaFinalRegiaoAtendidaTexto.getVisibility() == View.VISIBLE){
					txtRotaFinalRegiaoAtendida.setText("+ REGIÃO ATENDIDA");
					Util.collapse(txtRotaFinalRegiaoAtendidaTexto);
				}
				else{
					txtRotaFinalRegiaoAtendida.setText("- REGIÃO ATENDIDA");
					Util.expand(txtRotaFinalRegiaoAtendidaTexto);
				}
				break;
			case R.id.txtRotaFinalItinerario:
				if(txtRotaFinalItinerarioTexto.getVisibility() == View.VISIBLE){
					txtRotaFinalItinerario.setText("+ ITINERÁRIO");
					Util.collapse(txtRotaFinalItinerarioTexto);
				}
				else{
					txtRotaFinalItinerario.setText("- ITINERÁRIO");
					Util.expand(txtRotaFinalItinerarioTexto);
				}
				break;
			case R.id.txtRotaFinalTempoPercurso:
				if(txtRotaFinalTempoPercursoTexto.getVisibility() == View.VISIBLE){
					txtRotaFinalTempoPercurso.setText("+ TEMPO DE PERCURSO");
					Util.collapse(txtRotaFinalTempoPercursoTexto);
				}
				else{
					txtRotaFinalTempoPercurso.setText("- TEMPO DE PERCURSO");
					Util.expand(txtRotaFinalTempoPercursoTexto);
				}
				break;
				
			case R.id.txtHorarios:
				if(llHorarios.getVisibility() == View.VISIBLE){
					txtHorarios.setText("+ Horários");
					Util.collapse(llHorarios);
				}
				else{
					txtHorarios.setText("- Horários");
					Util.expand(llHorarios);
				}
				break;
				
			}
			
			
		}
	}


}
