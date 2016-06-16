package truegamers.queaula;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;

import java.util.Objects;

/**
 * Created by giovanni on 17/08/2015.
 */
public class ConfAulas {
    private static String Professor, NumSala;
    private static String Materia;
    private static String Sigla;
    private static String Sala;
    private static String Predio;
    private static String ProfessorDois;
    private static String MateriaDois;
    private static String SiglaDois;
    private static String SalaDois;
    private static String PredioDois;


    @SuppressLint("NewApi")
    public static void  ConfAulas(String Hoje) {
        NumSala= "362";
        if (Objects.equals(Hoje, "Segunda")) {
            Professor = "Forçan";
            Materia = "Modelagem De Sist Orient A Obj";
            Sigla = "MSOO";
            Sala = NumSala;
            Predio = "B";

            ProfessorDois = "Lucia";
            MateriaDois = "Gestão de Projetos";
            SiglaDois = "GP";
            SalaDois = NumSala;
            PredioDois = "B";
        }

        if (Objects.equals(Hoje, "Terça")) {
            Professor = "Oba!";
            Materia = "Aula Vaga";
            Sigla = "Descanse";
            Sala = "00";
            Predio = "";

            ProfessorDois = "Oba!";
            MateriaDois = "Aula Vaga";
            SiglaDois = "Descanse";
            SalaDois = "00";
            PredioDois = "";
        }
        if (Objects.equals(Hoje, "Quarta")) {
            Professor = "Caruso";
            Materia = "Redes de Computadores";
            Sigla = "RC";
            Sala = NumSala;
            Predio = "B";

            ProfessorDois = "Caruso";
            MateriaDois = "Arquitetura de Comps";
            SiglaDois = "ARQ";
            SalaDois = NumSala;
            PredioDois = "B";
        }

        if (Objects.equals(Hoje, "Quinta")) {
            Professor = "Mariano";
            Materia = "Sistemas Operacionais";
            Sigla = "S.O.";
            Sala = NumSala;
            Predio = "B";

            ProfessorDois = "Mariano";
            MateriaDois = "Sistemas Operacionais";
            SiglaDois = "S.O. (Q)";
            SalaDois = "LAB/ "+NumSala;
            PredioDois = "7,8/ B";
        }
        if (Objects.equals(Hoje, "Sexta")) {
            Professor = "Mara";
            Materia = "Economia";
            Sigla = "ECON";
            Sala = "604";
            Predio = "B";

            ProfessorDois = "Chau";
            MateriaDois = "Teoria dos Grafos";
            SiglaDois = "TG";
            SalaDois = NumSala;
            PredioDois = "B";
        }
        if (Objects.equals(Hoje, "Sabado")) {
            Professor = "Oba!";
            Materia = "Sem mais!";
            Sigla = "Vai dormir!";
            Sala = "";
            Predio = "";

            ProfessorDois = "Oba!";
            MateriaDois = "Sem mais!";
            SiglaDois = "Vai Dormir!";
            SalaDois = "";
            PredioDois = "";
        }
        if (Objects.equals(Hoje, "Domingo")) {
            Professor = "Oba!";
            Materia = "Sem mais!";
            Sigla = "Vai dormir!";
            Sala = "";
            Predio = "";

            ProfessorDois = "Oba!";
            MateriaDois = "Sem mais!";
            SiglaDois = "Vai Dormir!";
            SalaDois = "";
            PredioDois = "";
        }
    }

    public static String getProfessor()
    {
        return Professor;
    }

    public static String getMateria()
    {
        return Materia;
    }

    public static String getSigla()
    {
        return Sigla;
    }

    public static String getSala()
    {
        return Sala;
    }

    public static String getPredio()
    {
        return Predio;
    }

    public static String getProfessorDois()
    {
        return ProfessorDois;
    }

    public static String getMateriaDois()
    {
        return MateriaDois;
    }

    public static String getSiglaDois()
    {
        return SiglaDois;
    }

    public static String getSalaDois()
    {
        return SalaDois;
    }

    public static String getPredioDois()
    {
        return PredioDois;
    }
}
