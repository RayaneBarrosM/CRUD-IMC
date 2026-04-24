package br.gov.sp.cps.cadastro;


import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper {

//variaveis de tabel e dados

    public static final String DATABASE_NAME = "dados.db";
    public static final String TABLE_NAME = "cadastro_imc";

    public static final String COL_1 = "ID";

    public static final String COL_2 = "Nome";
    public static final String COL_3 = "Idade";

    public static final String COL_4 = "Altura";

    public static final String COL_5 = "Peso";
    public static final String COL_6 = "IMC";

    // construtor de classe que chama a super classe??? acho que a superclasse é o banco de dados
    public DataBaseHelper (Context context) {super(context, DATABASE_NAME, null, 1);}

    //Metodo que chama ??? ao criar o db

    //Criando tabela
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE_TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, IDADE INTERGER, ALTURA DOUBLE, PESO INTEGER, IMC TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public void onUpgrade(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Metodos

    //Metodo gravar
    public boolean gravarDados(String nome, int idade, double altura, int peso){
        SQLiteDatabase db = this.getReadableDatabase();

        //Armazena os valores a serem inseridos
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nome);
        contentValues.put(COL_3, idade);
        contentValues.put(COL_4, altura);
        contentValues.put(COL_4, peso);

        //Insere os dados e armazena o resultado
        long resultado = db.insert(TABLE_NAME, null, contentValues);

        db.close();

        //retorna true se a inserção for bem sucedida
        return resultado != -1;

    }

    //Metodo consultar
    public Cursor obterIdadeporNome(String nome){
        //db no modo leitura
        SQLiteDatabase SQLiteDatabase db = this.getReadableDatabase();

        //Especificar coluna idade, peso e altura
        String[] colums = {COL_3, COL_4, COL_5};
        String selection = COL_2 + " = ?";
        String[] selectionArgs = {nome};

        return db.query(TABLE_NAME, colums, selection, selectionArgs, null, null, null);

    }

    //Metodo atualizar
    public boolean atualizarDados(String Nome, int novaIdade){
        SQLiteDatabase db = this.getWritabledatabase();

        //armazena novo valor para idade
        ContentValues valores = new ContentValues();
        valores.put(COL_3, novaIdade);

        //armazena novo valor para altura
        int linhasAfetadas = db.update(TABLE_NAME, valores, COL_2 + "=?",
          new String[]{}
        );

        //armazena novo valor para peso
        db.close();

        return linhasAfetadas > 0;

    }

    //Metodo deletar
    public boolean deletarDados(String nome){
        SQLiteDatabase db = this.getWritabledatabase();

    }


}