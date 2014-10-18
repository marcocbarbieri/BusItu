package br.com.tcc.busitu.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.LinhaDAO;
import br.com.tcc.busitu.util.Util;

public class NavegarActivity extends Fragment {

	TextView tvNavegar;
	TextView tvPesquisar;
	EditText etPesquisar;
	EditText etNavegarInicio;
	EditText etNavegarFim;
	Button btnPesquisar;
	Button btnNavegar;
	RadioGroup cbPesquisar;
	RadioGroup cbNavegarPartida;
	RadioGroup cbNavegarDestino;

	LinearLayout llNavegar;
	LinearLayout llPesquisar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_navegar, container,
				false);
		tvNavegar = (TextView) rootView.findViewById(R.id.labelNavegar);
		tvPesquisar = (TextView) rootView.findViewById(R.id.labelPesquisar);
		llNavegar = (LinearLayout) rootView.findViewById(R.id.llRota);
		llPesquisar = (LinearLayout) rootView.findViewById(R.id.llPesquisar);
		etPesquisar = (EditText) rootView.findViewById(R.id.textPesquisar);
		etNavegarInicio = (EditText) rootView.findViewById(R.id.textInicio);
		etNavegarFim = (EditText) rootView.findViewById(R.id.textFim);
		btnPesquisar = (Button) rootView.findViewById(R.id.btnPesquisar);
		btnNavegar = (Button) rootView.findViewById(R.id.btnNavegar);
		cbPesquisar = (RadioGroup) rootView.findViewById(R.id.checkBoxPesquisar);
		cbNavegarPartida = (RadioGroup) rootView.findViewById(R.id.checkBoxNavegarPartida);
		cbNavegarDestino = (RadioGroup) rootView.findViewById(R.id.checkBoxNavegarDestino);
		

		llNavegar.setVisibility(View.GONE);
		llPesquisar.setVisibility(View.GONE);

		btnPesquisar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String item = etPesquisar.getText().toString();
				LinhaDAO linhaDAO = new LinhaDAO(getActivity().getApplicationContext());
				LinhaBean linhaBean = new LinhaBean();
				
				
				Intent i = new Intent();
				Bundle b = new Bundle();

				switch (cbPesquisar.getCheckedRadioButtonId()) {

				case R.id.checkBoxPesquisarBairro:
					if (linhaDAO.buscaLinhaPorBairro(item)) {
						linhaBean.setResultado(linhaDAO.getResultado());
						b.putSerializable("LinhaBean", linhaBean);
						i.putExtras(b);
						i.setClass(getActivity().getApplicationContext(),
								LinhasResultFragment.class);
						startActivity(i);
					} else {
						Toast.makeText(getActivity().getApplicationContext(),
								"Nenhum resultado encontrado",
								Toast.LENGTH_SHORT).show();
					}
					break;

				case R.id.checkBoxPesquisarRua:

					if (linhaDAO.buscaLinhaPorRua(item)) {
						linhaBean.setResultado(linhaDAO.getResultado());
						b.putSerializable("LinhaBean", linhaBean);
						i.putExtras(b);
						i.setClass(getActivity().getApplicationContext(),
								LinhasResultFragment.class);
						startActivity(i);
					} else {
						Toast.makeText(getActivity().getApplicationContext(),
								"Nenhum resultado encontrado",
								Toast.LENGTH_SHORT).show();
					}
					break;
					
				case R.id.checkBoxPesquisarLinha:
					if (linhaDAO.buscarLinhaPorLinha(item)) {
						linhaBean.setResultado(linhaDAO.getResultado());
						b.putSerializable("LinhaBean", linhaBean);
						i.putExtras(b);
						i.setClass(getActivity().getApplicationContext(),
								LinhasResultFragment.class);
						startActivity(i);
					} else {
						Toast.makeText(getActivity().getApplicationContext(),
								"Nenhum resultado encontrado",
								Toast.LENGTH_SHORT).show();
					}
					break;
				}

			}
		});

		btnNavegar.setOnClickListener(new View.OnClickListener() {
			
			
			
			
			@Override
			public void onClick(View v) {
				
				String partida = etNavegarInicio.getText().toString();
				String destino = etNavegarFim.getText().toString();
				String colunaPartida = "rota";
				String colunaDestino = "rota";
				
				LinhaDAO linhaDAO = new LinhaDAO(getActivity().getApplicationContext());
				LinhaBean linhaBean = new LinhaBean();
				
				
				Intent i = new Intent();
				Bundle b = new Bundle();

				switch (cbNavegarPartida.getCheckedRadioButtonId()) {
				
				case R.id.checkBoxNavegarPartidaBairro:
					colunaPartida = "regiao_atendida";
					
					break;
				case R.id.checkBoxNavegarPartidaRua:
					colunaPartida = "rota";
					break;
				
				}
				
				switch (cbNavegarDestino.getCheckedRadioButtonId()){
				
				case R.id.checkBoxBairroDestino:
					colunaDestino = "regiao_atendida";
					break;
				case R.id.checkBoxRuaDestino:
					colunaDestino = "rota";
					break;
				
				}
				
				if (linhaDAO.buscarRota(partida, destino, colunaPartida, colunaDestino)
						&& !TextUtils.isEmpty(partida) && !TextUtils.isEmpty(destino)) {
					linhaBean.setResultado(linhaDAO.getResultado());
					b.putSerializable("LinhaBean", linhaBean);
					i.putExtras(b);
					i.setClass(getActivity().getApplicationContext(),
							LinhasResultFragment.class);
					startActivity(i);
				} else {
					Toast.makeText(getActivity().getApplicationContext(),
							"Não foram encontradas linhas que atendam estes endereços.",
							Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
		
		tvNavegar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (llNavegar.getVisibility() == View.VISIBLE) {
					Util.collapse(llNavegar);
				} else {
					Util.expand(llNavegar);
				}

			}
		});

		tvPesquisar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (llPesquisar.getVisibility() == View.VISIBLE) {
					Util.collapse(llPesquisar);
				} else {
					Util.expand(llPesquisar);
				}
			}
		});

		return rootView;

	}


}
