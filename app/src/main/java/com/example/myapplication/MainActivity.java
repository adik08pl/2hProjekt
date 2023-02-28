package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

private Button btnAtak1,btnAtak2,btnAtak3,btnAtak4;
private TextView lblHpPrzeciwnika,lblHpGracza,lblTarczaGracza,lblPrzeciwnik,lblAtakPrzeciwnika;
private Random random = new Random();
private int tura = 0,losowyPrzeciwnik,losowo,hpGracza=50,hpPrzeciwnika,hpPrzeciwnikaStart,tarcza=0;
    private List<Object> przeciwnicy = new ArrayList<>();
    private List<Integer> przeciwnicyHp = new ArrayList<>();
    private List<Integer> przeciwnicyAtak = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAtak1=(Button) findViewById(R.id.btnAtak1);
        btnAtak2=(Button) findViewById(R.id.btnAtak2);
        btnAtak3=(Button) findViewById(R.id.btnAtak3);
        btnAtak4=(Button) findViewById(R.id.btnAtak4);
        lblHpGracza=(TextView) findViewById(R.id.lblHpGracza);
        lblHpPrzeciwnika=(TextView) findViewById(R.id.lblHpPrzeciwnika);
        lblAtakPrzeciwnika=(TextView) findViewById(R.id.lblAtakPrzeciwnika);
        lblTarczaGracza=(TextView) findViewById(R.id.lblTarczaGracza);
        lblPrzeciwnik=(TextView) findViewById(R.id.lblPrzeciwnik);
        dodajPrzeciwnika("Goblin",10,15);
        dodajPrzeciwnika("Smok",100,20);
        dodajPrzeciwnika("Shrek",30,10);
        dodajPrzeciwnika("Grzyb",50,10);
        dodajPrzeciwnika("Elf",40,30);
        dodajPrzeciwnika("Bob",400,1);
        dodajPrzeciwnika("Król szczurów",21,25);
        dodajPrzeciwnika("Szczur",2,5);
        dodajPrzeciwnika("Mały smok",50,10);
        dodajPrzeciwnika("Drzewiec",100,20);
        dodajPrzeciwnika("Żywiołak ognia",30,49);
        dodajPrzeciwnika("Żywiołak wody",51,20);
        dodajPrzeciwnika("Mrówka",1,1);
        losowyPrzeciwnik = random.nextInt(przeciwnicy.size());
        lblPrzeciwnik.setText(""+przeciwnicy.get(losowyPrzeciwnik));
        lblAtakPrzeciwnika.setText("Atak: "+przeciwnicyAtak.get(losowyPrzeciwnik));
        lblHpPrzeciwnika.setText("Życie: "+przeciwnicyHp.get(losowyPrzeciwnik));
        lblHpGracza.setText("Życie: "+hpGracza);
        hpPrzeciwnika=przeciwnicyHp.get(losowyPrzeciwnik);
        hpPrzeciwnikaStart=hpPrzeciwnika;
        btnAtak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hpPrzeciwnika-=20;
                wykonajRuch("Atakujesz za 20 hp");
                sprawdzCzyKoniec();
            }
        });
        btnAtak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hpGracza+=15;
                wykonajRuch("Leczysz się za 15 hp");
                sprawdzCzyKoniec();
            }
        });
        btnAtak3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hpPrzeciwnika-=50;
                hpGracza/=2;
                wykonajRuch("Tracisz połowe HP ale zadajesz 50 hp");
                sprawdzCzyKoniec();
            }
        });
        btnAtak4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarcza+=20;
                wykonajRuch("Dajesz sobie tarcze (20 punktów)");
                sprawdzCzyKoniec();
            }
        });
    }

    private void sprawdzCzyKoniec() {
        if(hpGracza<=0&&hpPrzeciwnika<=0){
            Toast.makeText(this,"Remis",Toast.LENGTH_SHORT).show();
            btnAtak1.setEnabled(false);
            btnAtak2.setEnabled(false);
            btnAtak3.setEnabled(false);
            btnAtak4.setEnabled(false);
            Intent myActivity = new Intent(this, EndingActivity.class);
            myActivity.putExtra("WYNIK", "Remis, może kiedyś ci się uda");
            startActivity(myActivity);
        } else if (hpGracza<=0) {
            Toast.makeText(this,"Porażka",Toast.LENGTH_SHORT).show();
            btnAtak1.setEnabled(false);
            btnAtak2.setEnabled(false);
            btnAtak3.setEnabled(false);
            btnAtak4.setEnabled(false);
            Intent myActivity = new Intent(this, EndingActivity.class);
            myActivity.putExtra("WYNIK", "Porażka, brakuje ci szczęścia?");
            startActivity(myActivity);
        } else if (hpPrzeciwnika<=0) {
            Toast.makeText(this,"Wygrana",Toast.LENGTH_SHORT).show();
            btnAtak1.setEnabled(false);
            btnAtak2.setEnabled(false);
            btnAtak3.setEnabled(false);
            btnAtak4.setEnabled(false);
            Intent myActivity = new Intent(this, EndingActivity.class);
            myActivity.putExtra("WYNIK", "Gratulacje, wygrałeś");
            startActivity(myActivity);
        }
    }

    private void dodajPrzeciwnika(String nazwa, int hp, int atak) {
        przeciwnicy.add(nazwa);
        przeciwnicyAtak.add(atak);
        przeciwnicyHp.add(hp);
    }

    private void wykonajRuch(String trescAtaku) {
        Toast.makeText(this,trescAtaku,Toast.LENGTH_SHORT).show();
        losowo = random.nextInt(4);
        switch(losowo){
            case 0:
            Toast.makeText(this,przeciwnicy.get(losowyPrzeciwnik)+" atakuje za "+przeciwnicyAtak.get(losowyPrzeciwnik),Toast.LENGTH_SHORT).show();
            policzObrazenia(przeciwnicyAtak.get(losowyPrzeciwnik));
            break;
            case 1:
                Toast.makeText(this,przeciwnicy.get(losowyPrzeciwnik)+" leczy się za 20% swojego HP",Toast.LENGTH_SHORT).show();
                hpPrzeciwnika+=20/przeciwnicyHp.get(losowyPrzeciwnik);
                break;
            case 2:
                Toast.makeText(this,przeciwnicy.get(losowyPrzeciwnik)+" nie trafia swojego ataku",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,przeciwnicy.get(losowyPrzeciwnik)+" trafia krytycznie i zadaje dwa razy więcej niż normalnie ("+przeciwnicyAtak.get(losowyPrzeciwnik)*2+")",Toast.LENGTH_SHORT).show();
                policzObrazenia(przeciwnicyAtak.get(losowyPrzeciwnik)*2);
                break;
        }
        lblHpGracza.setText("Życie: "+hpGracza);
        lblHpPrzeciwnika.setText("Życie: "+hpPrzeciwnika);
        lblTarczaGracza.setText("Tarcza: "+tarcza);
    }

    private void policzObrazenia(int obrazenia) {
        if(tarcza-obrazenia>=0){
            tarcza-=obrazenia;
        }
        else{
            hpGracza-=obrazenia-tarcza;
            tarcza=0;
        }
    }
}