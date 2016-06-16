package truegamers.queaula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by giiozero on 26/04/2016.
 */
public class BancoController {

    private SQLiteDatabase db;
    private BancoCria banco;

    public BancoController(Context context){
        banco = new BancoCria(context);
    }

    public String insertAvisoDia(Boolean aviso_escolhe_dia){
        ContentValues valores; long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoCria.AVISO_ESCOLHE_DIA, aviso_escolhe_dia);
        resultado = db.insert(BancoCria.TABELA_CONFIGS, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao alterar aviso para "+ aviso_escolhe_dia.toString();
        else
            return "Aviso alterado para "+ aviso_escolhe_dia.toString()+" com Sucesso";
    }

    public Cursor carregaConfigs(){
        Cursor cursor;
        String[] campos = {banco.ID,banco.AVISO_ESCOLHE_DIA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_CONFIGS, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaTurma(){
        Cursor cursor;
        String[] campos = {banco.ID,banco.TRM_TURMA, banco.TRM_AULA, banco.TRM_DATA,banco.TRM_MATERIA, banco.TRM_PROF, banco.TRM_SALA, banco.TRM_SIGLA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_TURMA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


}



