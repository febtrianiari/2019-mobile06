package id.ac.polinema.idealbodyweight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import id.ac.polinema.idealbodyweight.Fragments.AboutFragment;
import id.ac.polinema.idealbodyweight.Fragments.BMIFragment;
import id.ac.polinema.idealbodyweight.Fragments.BrocaIndexFragment;
import id.ac.polinema.idealbodyweight.Fragments.MenuFragment;
import id.ac.polinema.idealbodyweight.Fragments.ResultFragment;

public class MainActivity extends AppCompatActivity implements
		MenuFragment.OnFragmentInteractionListener,
		BrocaIndexFragment.OnFragmentInteractionListener,
		ResultFragment.OnFragmentInteractionListener,
		BMIFragment.OnFragmentInteractionListener{

	// Deklarasikan atribut Fragment di sini
	private AboutFragment aboutFragment;
	private BrocaIndexFragment brocaIndexFragment;
	private BMIFragment bmiFragment;
	private ResultFragment resultFragment;
	MenuFragment menuFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aboutFragment = AboutFragment.newInstance("Febtria Niari Puan");
		menuFragment = new MenuFragment();
		resultFragment = new ResultFragment();
		brocaIndexFragment = new BrocaIndexFragment();
		bmiFragment = new BMIFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, menuFragment)
				.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return  true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		// TODO: Tambahkan penanganan menu di sini
		if (item.getItemId() == R.id.menu_about) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, aboutFragment)
					.addToBackStack(null)
					.commit();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBrocaIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, brocaIndexFragment)
				.commit();
	}


	@Override
	public void onBodyMassIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, bmiFragment)
				.commit();
	}

	@Override
	public void onCalculateBrocaIndexClicked(float index) {
		resultFragment.setInformation(String.format("Your ideal weight is %.2f kg", index));
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment)
				.commit();
	}

	@Override
	public void onTryAgainButtonClicked(String tag) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, brocaIndexFragment)
				.commit();
		if(tag.equals("Broca")){
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, brocaIndexFragment)
					.commit();
		}
		else if(tag.equals("BMI")){
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, bmiFragment)
					.commit();
		}
	}


	@Override
	public void onCalculateBMIClicked(String result) {
		resultFragment.setInformation(String.format("Your Body Mass " + result));
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment)
				.commit();
	}
}
