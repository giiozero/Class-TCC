package truegamers.queaula;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by giiozero on 26/04/2016.
 */
public class BancoCria extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
//TODAS TABELAS
    public static final String ID = "_id";
// Tabela Configs
    public static final String TABELA_CONFIGS = "configs", AVISO_ESCOLHE_DIA ="aviso_dia";
//Tabela Turma
    public static final String TABELA_TURMA="turma",TRM_TURMA="turma", TRM_DATA="data", TRM_SALA="sala", TRM_AULA="aula", TRM_PROF="prof", TRM_SIGLA="sigla", TRM_MATERIA="materia";
    private static final int VERSAO = 2;



    public BancoCria(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }
//
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlConfigs = "CREATE TABLE "+ TABELA_CONFIGS +"("
                +ID+" integer primary key autoincrement,"
                +AVISO_ESCOLHE_DIA+" boolean null);";
        db.execSQL(sqlConfigs);

        String sqlTurmas = "CREATE TABLE "+TABELA_TURMA+"("
                +ID+" integer primary key autoincrement,"
                +TRM_TURMA+" VARCHAR( 10 ) NULL,"
                +TRM_DATA+" integer(1) NULL,"
                +TRM_SALA+" VARCHAR(4) NULL,"
                +TRM_AULA+" integer(1) NULL,"
                +TRM_PROF+" VARCHAR(20) NULL,"
                +TRM_SIGLA+" VARCHAR(10) NULL,"
                +TRM_MATERIA+" VARCHAR(30) NULL,"
        +");";


        db.execSQL(sqlTurmas);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONFIGS);
        onCreate(db);
    }
}

