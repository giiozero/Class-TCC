package truegamers.queaula;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Random;


/**
 * Created by giovanni on 18/08/2015.
 */
public class ProfImages {
    public static Drawable drawable;
    private static Random random;
    public static Drawable[] drawables = null; // create a Drawables array that stores location of different images

    public static void SetImagem (ImageView Imagem, String Professor){
        if (Professor == "Mara") {   Imagem.setImageResource(R.drawable.mara);}
        else if (Professor == "Forçan") {   Imagem.setImageResource(R.drawable.forcan);}
        else if (Professor == "Lucia") {   Imagem.setImageResource(R.drawable.luciaa);}
        else if (Professor == "Natasha") {   Imagem.setImageResource(R.drawable.natashaa);}
        else {Imagem.setImageResource(R.drawable.ngm);}
    }
    public static void RandomizarOnClick(ImageView Imagem){
        random = new Random();
        int randomNumber = random.nextInt(drawables.length);
        drawable = drawables[randomNumber];
        Imagem.setImageDrawable(drawable); // set the image to the ImageView

    }



}
