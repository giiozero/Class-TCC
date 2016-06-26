package truegamers.queaula;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import static truegamers.queaula.FormataData.DataNumParaFeira;
import static truegamers.queaula.FormataData.FormataData_ObtemAuto;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//http://sec.unip.br/NotasFaltasMediaFinal.aspx?Tipo=ME&amp;Sistema=SEC

    //Menu Direito//
    private boolean escolhadata;
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    private Drawer navigationDrawerRight;
    private AccountHeaderBuilder headerNavigationLeft;
    //Menu Direito//
    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            String resultado = isChecked ? "true" : "false";
            Toast.makeText(MainActivity.this, "Aviso alterado para: " + resultado, Toast.LENGTH_SHORT).show();
            BancoController crud = new BancoController(getBaseContext());
            crud.insertAvisoDia(Boolean.parseBoolean(resultado));
            escolhadata= Boolean.parseBoolean(resultado);
        }
    };

    //Textos dos Profs
    TextView texto, textoDois;
    ImageView img, imgDois;

    //Conf de  Imagem
    private ImageView ProfImagem;
    private ImageView ProfImagemDois;

    //Botão da Semana
    private Spinner spnOpcoes;
    private boolean ApertouOBotao;

    //Para uso do BD - Turma
    private String Prof = null, Materia = null, Sigla = null, Sala = null, ProfDois = null, Turma = null;
    //Outros
    private TextView txtProf, txtSala, txtProfDois, txtSalaDois, txtApresentacao;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificaAviso();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //Todos os Views By Id
        ViewsById();
        //Obtém a data de hoje para valores do método @InserirTextos
        InserirTextos(FormataData_ObtemAuto());
        //Opções do para mudar o dia da semana
        SpinnerDias();

        //FAZER BOTÕES FUNCIONAREM
        //Botão para mudar o dia da semana
        spnOpcoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String DiaDaSemana = FormataData.DataNumParaFeira((String) spnOpcoes.getSelectedItem());
                if (DiaDaSemana.equals("Escolha um Dia")) { //Verifica Opção Selecionada no SPN
                    if (escolhadata == true) { //Consulta Banco de Dados
                       AlertaEscolhaData();
                    }
                } else {
                    //Para não dar bug com a data
                    ApertouOBotao = true;
                    //Insere valores de Textos nos Componentes
                    InserirTextos(FormataData.DataFeiraParaNum(DiaDaSemana()));
                    //Mostra mensagem
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "-feira selecionado(a).", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Atualiza as Datas
        InserirTextos(FormataData_ObtemAuto());

        //Menu Direito//
        //Navigation Drawer
        navigationDrawerRight = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        //new PrimaryDrawerItem().withName("Pate").withIcon(getResources().getDrawable(R.drawable.side_nav_bar)),
                        //new DividerDrawerItem(),
                        //new PrimaryDrawerItem().withName("Pote").withIcon(R.drawable.side_nav_bar),
                        //new SecondaryDrawerItem().withName("Pute").withIcon(R.drawable.side_nav_bar),
                        new SectionDrawerItem().withName("Configurações"),
                        new SwitchDrawerItem().withName("Aviso Inicial").withChecked(escolhadata).withOnCheckedChangeListener(mOnCheckedChangeListener)
                        //new ToggleDrawerItem().withName("Pute").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener)
                )
                .withToolbar(mToolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.END)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();
        //Menu Direito//
    }

    private void setDadosData (final TextView txtView, ImageView img, final String Sigla, final String Materia) {
        assert img != null;
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        assert txtView != null;
                        txtView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        txtView.setText("- Sigla " + Sigla + "\n- Matéria: " + Materia);
                        break;
                    case MotionEvent.ACTION_UP:
                        assert txtView != null;
                        txtView.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
                        txtView.setText("");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            System.exit(0);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            navigationDrawerRight.openDrawer();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void AlertaEscolhaData() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Atenção!");
        alertDialog.setMessage("Hoje é: " + FormataData_ObtemAuto() + ". \n Caso queira ver outro dia, escolha a data no botão inferior!");
        alertDialog.setButton2("Desabilitar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BancoController crud = new BancoController(getBaseContext());
                String resultado;
                resultado = crud.insertAvisoDia(false);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });
        alertDialog.setIcon(R.drawable.forcan);
        alertDialog.show();
    }

    private void ViewsById() {
         texto = (TextView) findViewById(R.id.txtInfo);
         img = (ImageView) findViewById(R.id.imgInfo);
         textoDois = (TextView) findViewById(R.id.txtInfoDois);
         imgDois = (ImageView) findViewById(R.id.imgInfoDois);

        spnOpcoes = (Spinner) findViewById(R.id.spnOpcoes);
        //Textos
        txtApresentacao = (TextView) findViewById(R.id.txtApresentacao);
        txtProf = (TextView) findViewById(R.id.txtProf);
        txtSala = (TextView) findViewById(R.id.txtSala);
        txtProfDois = (TextView) findViewById(R.id.txtProfDois);
        txtSalaDois = (TextView) findViewById(R.id.txtSalaDois);
        ProfImagem = (ImageView) findViewById(R.id.imgvProf);
        ProfImagemDois = (ImageView) findViewById(R.id.imgvProfDois);
    }

    private void SpinnerDias() {
        ArrayAdapter<String> adpOpcoes = new ArrayAdapter<String>(this, android.R.layout.browser_link_context_header);
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adpOpcoes.add("Escolha um Dia");
        adpOpcoes.add("Segunda");
        adpOpcoes.add("Terça");
        adpOpcoes.add("Quarta");
        adpOpcoes.add("Quinta");
        adpOpcoes.add("Sexta");
        spnOpcoes.setAdapter(adpOpcoes);
    }

    private void InserirTextos(String Data) {
        //Coloca o Título.
        txtApresentacao.setText(Turma+" - Noite (" + DataNumParaFeira(Data) + ")");
        //** 1º QUADRO ** //
        //Pega Dados do BD
        CarregarAula(Data, "1", Prof, Sigla, Sala, Materia, txtProf, txtSala);
        //Coloca valores nos Campos "On Touch"
        setDadosData (texto, img, Sigla, Materia);
        //Adiciona Imagem do Prof
        ProfImages.SetImagem(ProfImagem, Prof);

        //** 2º QUADRO ** //
        //Pega Dados do BD pra segunda Aula
        CarregarAula(Data, "2", Sala, Sigla, Sala, Materia, txtProfDois, txtSalaDois);
        //Coloca valores nos Campos "On Touch"
        setDadosData(textoDois, imgDois, Sigla,Materia);
        //Adiciona Imagem do Prof
        ProfImages.SetImagem(ProfImagemDois, ProfDois);
    }

    private void CarregarAula(String Data, String aula, String Prof, String Sigla, String Sala, String Materia, TextView txtProf, TextView txtSala) {
        Cursor cursor = CarregaAulaBD(Data, aula);
        if (cursor.getCount() > 0 ) {
            String Turma = cursor.getString(cursor.getColumnIndexOrThrow(BancoCria.TRM_TURMA));
            Prof = cursor.getString(cursor.getColumnIndexOrThrow(BancoCria.TRM_PROF));
            Sigla = cursor.getString(cursor.getColumnIndexOrThrow(BancoCria.TRM_SIGLA));
            Sala = cursor.getString(cursor.getColumnIndexOrThrow(BancoCria.TRM_SALA));
            Materia = cursor.getString(cursor.getColumnIndexOrThrow(BancoCria.TRM_MATERIA));
            txtProf.setText("Prof " + Prof + "\nMat: " + Sigla);
            txtSala.setText("Sala: " + Sala);

            this.Turma = Turma;
            this.Sigla = Sigla;
            this.Materia = Materia;
        } else {
            Toast.makeText(MainActivity.this, "Não foi encontrado aula para este dia: "+ DataNumParaFeira(Data)+"\n Verifique se essa informação está correta.", Toast.LENGTH_SHORT).show();
        }
    }

    private Cursor CarregaAulaBD(String Data, String Aula) {
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor= crud.carregaTurma(Data, Aula);
        return cursor;
    }
    private String DiaDaSemana() {
        String diaDaSemana;
        if (ApertouOBotao) {
            diaDaSemana = FormataData.DataNumParaFeira((String)spnOpcoes.getSelectedItem());
            ApertouOBotao = false;
        } else {
            diaDaSemana = FormataData.DataNumParaFeira(FormataData_ObtemAuto());
        }
        return diaDaSemana;
    }

    private void verificaAviso () {
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaConfigs();
        //Verifica se houve resultado
        if (cursor.moveToFirst()) {
            cursor.moveToLast();
            String MostraAlerta = cursor.getString(cursor.getColumnIndexOrThrow(BancoCria.AVISO_ESCOLHE_DIA));
            //Verifica se o resultado é positivo
            if (Integer.parseInt(MostraAlerta) == 1) {escolhadata= true;}
        } else {escolhadata = false;}
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://truegamers.queaula/http/host/path")
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://truegamers.queaula/http/host/path")
        );

    }
}
