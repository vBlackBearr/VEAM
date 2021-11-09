/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawers;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import com.sun.opengl.util.texture.Texture;
//import org.yourorghere.MunecoNieve;

/**
 *
 * @author A
 */
public class MuñecoNieve {

    //precision and global variables
    private static final int SLICES = 40;
    private static final int STACKS = 40;
    private GLUquadric q = null;
    private static int mvt = 0;

    //heigth and widht of each components
    private static final float HEIGHT_BODY = 0.5f;
    private static final float TOP_BODY = 0.35f;
    private static final float base = 0.05f;
    private static final float BOTTOM_BODY = 0.40f;
    private static final float pico = 0.01f;
    private static final float BOTTOM_BODY1 = 0.55f;
    private static final float BOTTOM_BODY2 = 0.40f;
    private static final float HEIGHT_LEGS = 0.2f;
    private static final float WIDTH_LEGS = 0.185f;
    private static final float HEIGHT_ARMS = 0.70f;
    private static final float WIDTH_ARMS = 0.045f;
    private static final float WIDTH_HEAD = 0.4f;
    private static final float WIDTH_HEAD1 = 0.5f;
    private static final float WIDTH_HEAD2 = 0.6f;
    private static final float WIDTH_EYES = 0.3f;
    private static final float WIDTH_HANDS = 0.1f;
    private static final float WIDTH_FINGERS = 0.0525f;
    private static final float WIDTH_SHOES = 0.28f;
    private static final float HEIGHT_SHOES = 0.05f;
    private static final float WIDTH_OPEN_MOUTH = 0.1f;
    private static final float WIDTH_BUTTONS = 0.0525f;
    private static final float SPACE_BETWEEN_BUTTONS = 0.12f;
    private static final float WIDTH_BOTTOM_BONNET = 0.403f;
    private static final float HEIGHT_BOTTOM_BONNET = 0.12f;
    private static final float WIDTH_BONNET = 0.52f;
    private static final float WIDTH_PUPILS = 0.06f;
    private static final float Chiapas = 0.05f;
    private static final float WIDTH_POMPON = 0.12f;
    private static final float NARIZ1 = 0.06f;
    private static final float BOCA = 0.025f;

    //position of each component int the window
    public MuñecoNieve() {
    }

    //Moviminetos 
    public void nieves(GL gl, boolean[] acciones) {
        
        boolean viento=acciones[0];
        boolean triste=acciones[1];
        boolean enojo=acciones[2];
        boolean asombro=acciones[3];
        boolean saludar=acciones[4];
        boolean caerc=acciones[5];
        boolean malo=acciones[6];
        boolean serio=acciones[7];
        
//              boolean viento, boolean triste, boolean enojo, boolean asombro, boolean saludar, boolean caerc,
//            boolean malo, boolean serio
        GLU glu = new GLU();
        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);

        //muñeco con viento
        set_white_material(gl);
        if (viento && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, 'V', 'V');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, 'V');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');
        } else if (viento && mvt % 20 + 10 <= 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, 'V', 'V');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, 'V');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');

        } //muñeco esta triste 
        else if (triste && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, 'T');
            nieve_head(gl, glu, true, false, ' ', ' ');
        } else if (triste && mvt % 20 + 10 <= 20) {
            //cambioF(gl, glu, MunecoNieve.nieveA, MunecoNieve.nieveA, MunecoNieve.cielo, MunecoNieve.nieveA, MunecoNieve.nieveA, MunecoNieve.nievep);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, 'T');
            nieve_head(gl, glu, true, false, ' ', ' ');
        } //muñeco esta enojado
        else if (enojo && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.lavap, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.lavap);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, 'E');
            draw_arm_right(gl, glu, 'E');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, true, ' ', ' ');
        } else if (asombro && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.Arbol, MunecoNieve.Arbol, MunecoNieve.cielo, MunecoNieve.Arbol, MunecoNieve.Arbol, MunecoNieve.nievep);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, 'A');
            nieve_head(gl, glu, false, false, ' ', ' ');
        } else if (saludar && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, 'H');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');
            nieve_head(gl, glu, true, false, 'H', ' ');
        } else if (saludar && mvt % 20 + 10 <= 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');
            nieve_head(gl, glu, true, false, ' ', ' ');
        } else if (caerc && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', 'C');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, 'C');
            boca(gl, glu, 'C');
            nieve_head(gl, glu, false, false, 'C', 'C');
        } else if (caerc && mvt % 20 + 10 <= 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', 'C');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, 'C');
            boca(gl, glu, 'C');
            nieve_head(gl, glu, false, false, 'C', 'C');
            //Malo
        } else if (malo && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.lavap, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.lavap);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, 'D');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, true, 'D', ' ');
        } else if (malo && mvt % 20 + 10 <= 20) {
            //cambioF(gl, glu, MunecoNieve.lavap, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.fuego, MunecoNieve.lavap);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, 'D');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, true, 'D', ' ');
        } else if (serio && mvt % 20 + 10 > 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, 'I');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, 'I');
            nieve_head(gl, glu, false, false, ' ', ' ');
            nieve_head(gl, glu, false, false, 'I', ' ');
        } else if (serio && mvt % 20 + 10 <= 20) {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');
        } else if (caerc && mvt % 20 + 10 > 20) {
//         
//         //muñeco esta normal 
        } else {
            //cambioF(gl, glu, MunecoNieve.tAtras, MunecoNieve.tPiso, MunecoNieve.tSuperior, MunecoNieve.tDerecha, MunecoNieve.tIzquierda, MunecoNieve.tEnfrente);
            nieve_body(gl, glu, ' ', ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            nieve_hat(gl, glu, ' ');
            boca(gl, glu, ' ');
            nieve_head(gl, glu, false, false, ' ', ' ');
        }

        mvt++;

    }

    //Cuerpo
    public void nieve_body(GL gl, GLU glu, char v, char c) {
        //Nariz 
        if (c == ' ') {
            set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, 0.6f, 0.350f);
            glu.gluSphere(q, NARIZ1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (c == 'V') {
            set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.08f, 0.6f, 0.350f);
            glu.gluSphere(q, NARIZ1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (c == 'C') {
            set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.45f, -0.9f, 0.350f);
            glu.gluSphere(q, NARIZ1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (v == ' ') {
            //Botones panza media
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, 0.0f, 0.450f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.0f, -SPACE_BETWEEN_BUTTONS, 0.012f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.0f, -SPACE_BETWEEN_BUTTONS, -0.02f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //Botones panza baja
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, -0.8f, 0.560f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.0f, -SPACE_BETWEEN_BUTTONS, 0.012f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.0f, -SPACE_BETWEEN_BUTTONS, -0.02f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }
        if (v == 'V') {
            //Botones panza media
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.06f, 0.0f, 0.450f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.02f, -SPACE_BETWEEN_BUTTONS, 0.012f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.05f, -SPACE_BETWEEN_BUTTONS, -0.02f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //Botones panza baja
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.06f, -0.8f, 0.560f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.05f, -SPACE_BETWEEN_BUTTONS, 0.012f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glTranslatef(0.3f, -SPACE_BETWEEN_BUTTONS, -0.02f);
            glu.gluSphere(q, WIDTH_BUTTONS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
    }

    //Cabeza
    public void nieve_head(GL gl, GLU glu, boolean sad, boolean Enojo, char n, char o) {

        if (n == ' ') {
            //cabeza
            set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.7f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //Panza media
            set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.1f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //panza baja
            set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.9f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD2, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (n == 'C') {
            //cabeza
            set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.5f, -0.9f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //Panza media
            set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.1f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //panza baja
            set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.9f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD2, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (n == 'D') {
            //cabeza
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.7f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //Panza media
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.1f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            //panza baja
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.9f, 0.0f);
            glu.gluSphere(q, WIDTH_HEAD2, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }

//        //Ojos
        if (o == ' ') {
            set_grey_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.1f, 0.70f, 0.360f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.25f, 0f, -0.02f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (o == 'C') {
            set_grey_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.6f, -0.80f, 0.360f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(-0.25f, 0f, -0.02f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }

        //create amgry slahsseyes
        if (Enojo == true) {
            //left
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.07f, 0.68f, 0.300f);
            gl.glRotatef(45f, 0f, 0f, 1f);
            gl.glScalef(0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();

            //rigth
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.03f, 0.68f, 0.300f);
            gl.glRotatef(135f, 0f, 0f, 1f);
            gl.glScalef(0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();

        }

//      chapas
        if (n == 'H') {
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.13f, 0.57f, 0.315f);
            glu.gluSphere(q, Chiapas, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.18f, 0.57f, 0.294f);
            glu.gluSphere(q, Chiapas, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }

        if (n == 'I') {
            //left
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.07f, 0.80f, 0.280f);
            gl.glRotatef(0f, 0f, 0f, 1f);
            gl.glScalef(0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();

            //rigth
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.03f, 0.79f, 0.295f);
            gl.glRotatef(180f, 0f, 0f, 1f);
            gl.glScalef(0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
        }

    }

    public void boca(GL gl, GLU glu, char t) {
        //Boca
        if (t == ' ') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, 0.45f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.05f, 0.46f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.09f, 0.46f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.11f, 0.48f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.15f, 0.48f, 0.305f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        set_white_material(gl);

        if (t == 'T') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, 0.43f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.05f, 0.42f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.09f, 0.42f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.11f, 0.39f, 0.308f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.15f, 0.39f, 0.305f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_blue_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.1f, 0.60f, 0.360f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_blue_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.1f, 0.54f, 0.340f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_blue_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.16f, 0.60f, 0.340f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            set_blue_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.16f, 0.54f, 0.320f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }
        if (t == 'A') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, 0.45f, 0.200f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.05f, 0.46f, 0.200f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.09f, 0.46f, 0.200f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.11f, 0.48f, 0.200f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.15f, 0.48f, 0.200f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            gl.glPushMatrix();
            set_black_material(gl);
            gl.glTranslatef(0.02f, 0.44f, 0.230f);
            glu.gluSphere(q, WIDTH_OPEN_MOUTH, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }

        if (t == 'C') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.46f, -1.06f, 0.365f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.39f, -1.05f, 0.365f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.53f, -1.05f, 0.365f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.33f, -1.03f, 0.365f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.59f, -1.03f, 0.362f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (t == 'I') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.02f, 0.45f, 0.305f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.05f, 0.45f, 0.305f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.09f, 0.45f, 0.305f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.11f, 0.45f, 0.305f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.15f, 0.45f, 0.303f);
            glu.gluSphere(q, BOCA, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
    }

    //Sombrero
    public void nieve_hat(GL gl, GLU glu, char s) {
        //Sombrero

        if (s == ' ') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 1.38f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 0.88f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, BOTTOM_BODY1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 1.38f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, BOTTOM_BODY2, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            //Cinta Sombrero
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 1.05f, -0.01f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_BOTTOM_BONNET, WIDTH_BOTTOM_BONNET, HEIGHT_BOTTOM_BONNET, 100, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            set_white_material(gl);
        }
        set_white_material(gl);

        if (s == 'V') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.5f, 1.38f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.5f, 0.88f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, BOTTOM_BODY1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.5f, 1.38f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, BOTTOM_BODY2, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            //Cinta Sombrero
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.5f, 1.05f, -0.01f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_BOTTOM_BONNET, WIDTH_BOTTOM_BONNET, HEIGHT_BOTTOM_BONNET, 100, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            set_white_material(gl);

        }
        set_white_material(gl);
        if (s == 'C') {
            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.5f, -0.17f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.5f, -0.67f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, BOTTOM_BODY1, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_black_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.5f, -0.17f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, BOTTOM_BODY2, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            //Cinta Sombrero
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(1.5f, -0.50f, -0.01f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_BOTTOM_BONNET, WIDTH_BOTTOM_BONNET, HEIGHT_BOTTOM_BONNET, 100, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
            set_white_material(gl);
        }
        if (s == 'D') {
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.15f, 1.38f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, pico, base, HEIGHT_BODY, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.15f, 1.38f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(q, pico, base, HEIGHT_BODY, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }

    }

    //Brazo Izquierdo
    public void draw_arm_left(GL gl, GLU glu, char c) {
        //we create left arm

        if (c == ' ') {
            set_brown_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.40f, -0.125f, 0f);
            gl.glRotatef(90f, 1f, -20.0f, 0f);
            glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }
        if (c == 'E') {
            set_brown_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.40f, -0.125f, 0f);
            gl.glRotatef(100f, -125f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }
        if (c == 'H') {
            set_brown_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.40f, -0.125f, 0f);
            gl.glRotatef(100f, -125f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }

    }
//    Brazo Derecho

    public void draw_arm_right(GL gl, GLU glu, char c) {
        if (c == ' ') {
            set_brown_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.40f, -0.125f, 0f);
            gl.glRotatef(90f, 1f, 8.0f, -0.0f);
            glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (c == 'E') {
            set_brown_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.40f, -0.125f, 0f);
            gl.glRotatef(100f, -125f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();
        }
        if (c == 'I') {
            set_brown_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.40f, -0.125f, 0f);
            gl.glRotatef(100f, -125f, 0f, 0f);
            glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glFlush();

        }

    }

    public void set_red_material(GL gl) {

        float[] mat_ambient = {0.8f, 0.05f, 0.15f, 0.2f};
        float[] mat_diffuse = {0.4f, 0.4f, 0.4f, 1.0f};
        float[] mat_specular = {0.7f, 0.6f, 0.6f, 1.0f};
        float shine = 15.0f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();
    }

    public void set_white_material(GL gl) {

        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();
    }

    public void set_blue_material(GL gl) {

        float mat_ambient[] = {0.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();
    }

    public void set_brown_material(GL gl) {

        float mat_ambient[] = {0.1f, 0.0f, 0.0f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();
    }

    public void set_grey_material(GL gl) {

        float mat_ambient[] = {0.07f, 0.07f, 0.07f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();
    }

    public void set_orange_material(GL gl) {

        float mat_ambient[] = {1.0f, 0.5f, 0.0f, 0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();
    }

    public void set_black_material(GL gl) {

        float mat_ambient[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_diffuse[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glFlush();

    }
    //Caja

    public void box(GL gl) {
        gl.glBegin(GL.GL_POLYGON);/* f1: front */
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glBegin(GL.GL_POLYGON);/* f2: bottom */
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glBegin(GL.GL_POLYGON);/* f3:back */
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glBegin(GL.GL_POLYGON);/* f4: top */
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glBegin(GL.GL_POLYGON);/* f5: left */
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glBegin(GL.GL_POLYGON);/* f6: right */
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glFlush();

    }

    public void fondo(GL gl, GLU glu, Texture t) {
        set_white_material(gl);
        int m = t.getTextureObject();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, m);
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0.0f, 1.0f);
        gl.glVertex3d(-5.0f, -5.0f, -5.0f);
        gl.glTexCoord2d(1.0f, 1.0f);
        gl.glVertex3d(5.0f, -5.0f, -5.0f);
        gl.glTexCoord2d(1.0f, .0f);
        gl.glVertex3d(5.0f, 5.0f, -5.0f);
        gl.glTexCoord2d(0.0f, 0.0f);
        gl.glVertex3d(-5.0f, 5.0f, -5.0f);
        gl.glEnd();
        gl.glFlush();
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glFlush();
        set_white_material(gl);
    }

    public void cambioF(GL gl, GLU glu, Texture e, Texture a, Texture s, Texture d, Texture i, Texture b) {
        gl.glFlush();
//        
        gl.glPushMatrix();
        gl.glScaled(2.0f, 1.0f, 2.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        fondo(gl, glu, a);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(2.0f, 1.0f, 2.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(-90, 1.0f, 0.0f, 0.0f);
        fondo(gl, glu, b);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(2.0f, 1.0f, 2.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
        fondo(gl, glu, s);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(2.0f, 1.0f, 2.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
        fondo(gl, glu, d);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(2.0f, 1.0f, 2.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
        fondo(gl, glu, i);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(2.0f, 1.0f, 2.0f);
        gl.glTranslatef(0.0f, 0.0f, 10.0f);
        gl.glRotatef(0, 0.0f, 0.0f, 1.0f);
        fondo(gl, glu, e);
        gl.glPopMatrix();
        gl.glFlush();
    }
}
