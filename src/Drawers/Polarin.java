package Drawers;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Polarin {

    //precision and global variables
    private static final int SLICES = 20;
    private static final int STACKS = 20;
    private GLUquadric GLUq = null;

    //heigth and widht of each components
    private static final float HEIGHT_BODY = 1f;
    private static final float TOP_BODY = 0.5f;
    private static final float BOTTOM_BODY = 0.5f;
    private static final float HEIGHT_LEGS = 0.5f;
    private static final float WIDTH_LEGS = 0.185f;
    private static final float HEIGHT_ARMS = 0.3f;
    private static final float WIDTH_ARMS = 0.09f;
    private static final float WIDTH_HEAD = 0.9f;
    private static final float WIDTH_EYES = 0.3f;
    private static final float WIDTH_Lagrima = 0.06f;
    private static final float WIDTH_OREJAS = 0.4f;
    private static final float WIDTH_NARIZ1 = 0.3f;
    private static final float WIDTH_NARIZ2 = 0.1f;
    private static final float WIDTH_HANDS = 0.1f;
    private static final float WIDTH_FINGERS = 0.0525f;
    private static final float WIDTH_SHOES = 0.35f;
    private static final float HEIGHT_SHOES = 0.05f;
    private static final float WIDTH_OPEN_MOUTH = 0.1f;
    private static final float WIDTH_PUPILS = 0.13f;
    private static final float WIDTH_ternura = 0.06f;

    //position of each component int the window
    public Polarin() {
        
    }

    public void dibujaPolarin(GL gl, boolean enojado, boolean sorprendido, boolean mareado, boolean feliz, boolean triste) {

        GLU glu = new GLU();
        GLUq = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(GLUq, GLU.GLU_FILL);
        glu.gluQuadricOrientation(GLUq, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(GLUq, GLU.GLU_SMOOTH);

        if (feliz) {
            draw_legs(gl, glu, 'F');
            draw_arm_left(gl, glu, 'F');
            draw_arm_right(gl, glu, 'F');
            draw_head(gl, glu, 'F');
            draw_body(gl, glu);
        } //enojo
        else if (enojado) {
            gl.glPushMatrix();
            draw_legs(gl, glu, ' ');
            draw_arm_left(gl, glu, 'E');
            draw_arm_right(gl, glu, 'E');
            draw_head(gl, glu, 'E');
            draw_body(gl, glu);
            gl.glPopMatrix();
        } //triste
        else if (triste) {
            draw_legs(gl, glu, ' ');
            draw_arm_left(gl, glu, 'T');
            draw_arm_right(gl, glu, ' ');
            draw_head(gl, glu, 'T');
            draw_body(gl, glu);
            //sorprendido
        } else if (sorprendido) {
            gl.glPushMatrix();
            draw_legs(gl, glu, 'S');
            draw_arm_left(gl, glu, 'S');
            draw_arm_right(gl, glu, 'S');
            draw_head(gl, glu, 'S');
            draw_body(gl, glu);
            gl.glPopMatrix();
        //mareado
        } else if (mareado) {
            gl.glPushMatrix();
            gl.glRotatef(-45f, 1f, 0f, 0f);
            draw_legs(gl, glu, 'M');
            draw_arm_left(gl, glu, 'M');
            draw_arm_right(gl, glu, ' ');
            draw_head(gl, glu, 'M');
            draw_body(gl, glu);
            gl.glPopMatrix();
        } //normal
        else {
            draw_legs(gl, glu, ' ');
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
        }
    }

    public void draw_body(GL gl, GLU glu) {
        //TRONCO
        set_grey_material(gl);
        gl.glPushMatrix();
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(GLUq, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY, SLICES, STACKS);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0.0f, -HEIGHT_BODY, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glPopMatrix();

        //cola
        set_grey_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0f, -0.85f, -.5f);
        glu.gluSphere(GLUq, WIDTH_NARIZ2, SLICES, STACKS);

        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glTranslatef(0f, .5f, .12f);
        glu.gluDisk(GLUq, 0f, HEIGHT_BODY / 2, SLICES, STACKS);
        gl.glPopMatrix();

    }

    public void draw_head(GL gl, GLU glu, char c) {

        //cabeza
        if (c != 'M') {
            blancoCabeza(gl);
        } else {
            verde(gl);
        }

        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.5f, 0f);
        glu.gluSphere(GLUq, WIDTH_HEAD, SLICES, STACKS);
        gl.glPopMatrix();

        //we create eyes (white)
        gl.glPushMatrix();
        if (c == ' ' || c == 'F') {
            set_grey_material(gl);
            gl.glTranslatef(-0.15f, 0.65f, 0.605f);
            glu.gluSphere(GLUq, WIDTH_EYES, SLICES, STACKS);
            gl.glTranslatef(0.350f, 0.0f, 0.0f);
            glu.gluSphere(GLUq, WIDTH_EYES, SLICES, STACKS);
        }
        if (c == 'E') {
            set_red_material(gl);
            gl.glTranslatef(-0.15f, 0.65f, 0.605f);
            glu.gluSphere(GLUq, WIDTH_EYES, SLICES, STACKS);
            gl.glTranslatef(0.350f, 0.0f, 0.0f);
            glu.gluSphere(GLUq, WIDTH_EYES, SLICES, STACKS);
        }

        gl.glPopMatrix();
        //Triste
        if (c == 'T') {
            //Lagrima
            azul(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.26f, 0.45f, 0.805f);
            glu.gluSphere(GLUq, WIDTH_Lagrima, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(-0.26f, 0.48f, 0.805f);
            glu.gluSphere(GLUq, WIDTH_Lagrima, SLICES, STACKS);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(-0.26f, 0.5f, 0.805f);
            glu.gluSphere(GLUq, WIDTH_Lagrima, SLICES, STACKS);
            gl.glPopMatrix();

        }

        //OREJAS
        set_grey_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.4f, 1.3f, -0.1f);
        glu.gluSphere(GLUq, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0.4f, 1.3f, -0.1f);
        glu.gluSphere(GLUq, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();

        //Nariz
        set_grey_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0f, 0.25f, 0.7f);
        glu.gluSphere(GLUq, WIDTH_NARIZ1, SLICES, STACKS);
        gl.glPopMatrix();

        negro(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0f, 0.25f, 0.98f);
        glu.gluSphere(GLUq, WIDTH_NARIZ2, SLICES, STACKS);
        gl.glPopMatrix();

        //we create mouth
        set_red_material(gl);
        gl.glPushMatrix();

        if (c == ' ' || c == 'M') {
            gl.glTranslatef(0.1f, -0.1f, 0.550f);
            gl.glRotatef(45f, 1f, 0f, 0f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
        }
        if (c == 'T' || c == 'E') {
            gl.glTranslatef(0.1f, -0.1f, 0.550f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);

        }
        if (c == 'S') {
            gl.glPushMatrix();
            set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, -0.12f, .6f);
            glu.gluSphere(GLUq, WIDTH_NARIZ2, SLICES, STACKS);
            gl.glPopMatrix();
        }
        if (c == 'F') {
            set_red_material(gl);
            gl.glTranslatef(0.0f, -0.1f, 0.58f);
            gl.glRotatef(180f, 0f, 0f, 0f);
            double[] cutplane = new double[]{0.0f, 1.0f, 0.0f, 0f};
            gl.glClipPlane(GL.GL_CLIP_PLANE2, cutplane, 0);
            gl.glEnable(GL.GL_CLIP_PLANE2);
            glu.gluSphere(GLUq, .15, 100, 100);
            gl.glDisable(GL.GL_CLIP_PLANE2);
        }
        gl.glPopMatrix();
        //we create eyes (black)
        negro(gl);
        gl.glPushMatrix();
        if (c == ' ') {
            gl.glTranslatef(-0.15f, 0.65f, 0.868f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            //Ternura de ojos
            gl.glPushMatrix();
            blanco(gl);
            gl.glTranslatef(-0.55f, 0.03f, 0.07f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glPopMatrix();

            gl.glPushMatrix();
            blanco(gl);
            gl.glTranslatef(-0.45f, -0.06f, 0.05f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glPopMatrix();
        }
        if (c == 'T') {
            gl.glTranslatef(-0.2f, 0.6f, 0.868f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0.f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
        }
        if (c == 'S') {
            gl.glPushMatrix();
            gl.glScalef(1.5f, 1.5f, 1.5f);
            gl.glTranslatef(-0.2f, .45f, 0.5f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0.f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
        }

        if (c == 'M') {
            gl.glPushMatrix();
            gl.glTranslatef(-0.1f, .8f, .8f);
            gl.glRotatef(45f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(-0.2f, .8f, .8f);
            gl.glRotatef(135f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(.3f, .8f, .8f);
            gl.glRotatef(45f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(0.2f, .8f, .8f);
            gl.glRotatef(135f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
        }

        if (c == 'E') {
            gl.glTranslatef(-0.2f, 0.65f, 0.868f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            //CEJAS
            gl.glPushMatrix();
            gl.glTranslatef(-0.1f, 0.4f, -0.05f);
            gl.glRotatef(45f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
            gl.glTranslatef(-.5f, 0.4f, -0.05f);
            gl.glRotatef(135f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
        }
        if (c == 'F') {
            gl.glTranslatef(-0.2f, 0.7f, 0.868f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0.f);
            glu.gluSphere(GLUq, WIDTH_PUPILS, SLICES, STACKS);
            //CEJAS
            gl.glPushMatrix();
            gl.glTranslatef(-.6f, 0.4f, -0.05f);
            gl.glRotatef(40f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
            gl.glTranslatef(0f, 0.4f, -0.05f);
            gl.glRotatef(130f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
        }
        if (c == 'T') {
            //CEJAS
            gl.glPushMatrix();
            gl.glTranslatef(-.5f, 0.4f, -0.05f);
            gl.glRotatef(45f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslatef(-0.1f, 0.4f, -0.05f);
            gl.glRotatef(135f, 0f, 0f, 1f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
            //Ternura de ojos
            gl.glPushMatrix();
            blanco(gl);
            gl.glTranslatef(-0.55f, 0.03f, 0.07f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glPopMatrix();

            gl.glPushMatrix();
            blanco(gl);
            gl.glTranslatef(-0.45f, -0.06f, 0.05f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glTranslatef(0.50f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_ternura, SLICES, STACKS);
            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }

    public void draw_legs(GL gl, GLU glu, char c) {

        //PIERNA IZQUIERDA
        set_grey_material(gl);
        gl.glPushMatrix();
        if (c == ' ' || c == 'E' || c == 'T' || c == 'F'|| c == 'S') {
            gl.glTranslatef(0.19f, -0.9f, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluCylinder(GLUq, WIDTH_LEGS, WIDTH_LEGS, HEIGHT_LEGS, SLICES, STACKS);
            glu.gluSphere(GLUq, WIDTH_LEGS, SLICES, STACKS);
            gl.glRotatef(90f, -1f, -1.5f, 0f);
            gl.glTranslatef(0f, -HEIGHT_LEGS, 0f);
            gl.glRotatef(90f, 1f, -1.5f, 0f);
        }
        gl.glPopMatrix();

        if (c == 'M') {
            gl.glPushMatrix();
            gl.glTranslatef(0.19f, -0.9f, 0f);
            gl.glRotatef(120f, 1f, 0f, 0f);
            glu.gluCylinder(GLUq, WIDTH_LEGS, WIDTH_LEGS, HEIGHT_LEGS, SLICES, STACKS);
            glu.gluSphere(GLUq, WIDTH_LEGS, SLICES, STACKS);
            gl.glRotatef(120f, -1f, -1.5f, 0f);
            gl.glTranslatef(0f, -HEIGHT_LEGS, 0f);
            gl.glRotatef(120f, 1f, -1.5f, 0f);
            gl.glPopMatrix();
        }

        //PIERNA DERECHA
        gl.glPushMatrix();
        gl.glTranslatef(-0.19f, -0.9f, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(GLUq, WIDTH_LEGS, WIDTH_LEGS, HEIGHT_LEGS, SLICES, STACKS);
        glu.gluSphere(GLUq, WIDTH_LEGS, SLICES, STACKS);
        gl.glRotatef(90f, -1f, -1.5f, 0f);
        gl.glTranslatef(0f, -HEIGHT_LEGS, 0f);
        gl.glRotatef(90f, 1f, -1.5f, 0f);
        gl.glPopMatrix();
        //we create shoes
        if (c != 'M') {
            AZUL(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.2f, -1.4f, 0.12f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, 0.14, SLICES, STACKS);
            glu.gluCylinder(GLUq, WIDTH_SHOES / 2, WIDTH_SHOES / 2, HEIGHT_SHOES, SLICES, STACKS);
            gl.glRotatef(90f, -1f, 0f, 0f);
            gl.glTranslatef(0f, -HEIGHT_SHOES, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, WIDTH_SHOES / 2, SLICES, STACKS);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(0.2f, -1.4f, 0.12f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, 0.14, SLICES, STACKS);
            glu.gluCylinder(GLUq, WIDTH_SHOES / 2, WIDTH_SHOES / 2, HEIGHT_SHOES, SLICES, STACKS);
            gl.glRotatef(90f, -1f, 0f, 0f);
            gl.glTranslatef(0f, -HEIGHT_SHOES, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, WIDTH_SHOES / 2, SLICES, STACKS);
            gl.glPopMatrix();
        } else {
            AZUL(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.2f, -1.4f, 0.12f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, 0.14, SLICES, STACKS);
            glu.gluCylinder(GLUq, WIDTH_SHOES / 2, WIDTH_SHOES / 2, HEIGHT_SHOES, SLICES, STACKS);
            gl.glRotatef(90f, -1f, 0f, 0f);
            gl.glTranslatef(0f, -HEIGHT_SHOES, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, WIDTH_SHOES / 2, SLICES, STACKS);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glRotatef(-10f, -.1f, 0f, 0f);
            gl.glTranslatef(0.2f, -1.4f, 0.12f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, 0.14, SLICES, STACKS);
            glu.gluCylinder(GLUq, WIDTH_SHOES / 2, WIDTH_SHOES / 2, HEIGHT_SHOES, SLICES, STACKS);
            gl.glRotatef(90f, -1f, 0f, 0f);
            gl.glTranslatef(0f, -HEIGHT_SHOES, 0f);
            gl.glRotatef(90f, 1f, 0f, 0f);
            glu.gluDisk(GLUq, 0f, WIDTH_SHOES / 2, SLICES, STACKS);

            gl.glPopMatrix();
        }

    }

    public void draw_arm_left(GL gl, GLU glu, char c) {

        gl.glPushMatrix();
        if (c == 'T' || c == 'F') {
            gl.glTranslatef(-0.86f, -0.48f, -0.05f);
            gl.glRotatef(45, 0f, -100f, 0f);
            glu.gluSphere(GLUq, WIDTH_HANDS, SLICES, STACKS);
            gl.glTranslatef(0.075f, 0.015f, 0.05f);
            gl.glRotatef(45, 0.f, -100f, 0f);
            glu.gluSphere(GLUq, WIDTH_FINGERS, SLICES, STACKS);
            gl.glRotatef(45, 0f, -100f, 0f);
        }

        if (c == 'E' || c == 'M') {
            gl.glTranslatef(-0.65f, -0.3f, .35f);
            gl.glRotatef(90, -1f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_HANDS, SLICES, STACKS);
            gl.glTranslatef(0.075f, 0.015f, 0.05f);
            gl.glRotatef(90, -1f, 0f, 0f);
            glu.gluSphere(GLUq, WIDTH_FINGERS, SLICES, STACKS);
            gl.glRotatef(90, -1f, 0f, 0f);
        }
        if (c == ' '|| c == 'S') {
            gl.glTranslatef(-0.65f, -0.6f, 0f);
            glu.gluSphere(GLUq, WIDTH_HANDS, SLICES, STACKS);
            gl.glTranslatef(0.055f, 0.015f, 0.05f);
            glu.gluSphere(GLUq, WIDTH_FINGERS, SLICES, STACKS);
        }
        gl.glPopMatrix();

        //we create left arm
        set_grey_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.57f, -0.3f, 0f);
        gl.glRotatef(90f, 1f, -0.20f, 0f);
        if (c == 'T') {
            gl.glRotatef(45, 0f, -100f, 0f);
        }
        if (c == 'E' || c == 'M') {
            gl.glRotatef(90, -1f, 0f, 0f);
        }
        if (c == 'F') {
            gl.glRotatef(45, 0f, -100f, 0f);
        }

        glu.gluCylinder(GLUq, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
        glu.gluSphere(GLUq, WIDTH_ARMS, SLICES, STACKS);
        gl.glRotatef(90f, -1f, 0.20f, 0f);
        gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);
        gl.glRotatef(90f, 1f, -0.20f, 0f);
        gl.glPopMatrix();
    }

    public void draw_arm_right(GL gl, GLU glu, char c) {

        blanco(gl);
        gl.glPushMatrix();

        if (c == ' ' || c == 'E') {
            AZUL(gl);
            gl.glTranslatef(0.6f, -0.6f, 0f);
            glu.gluSphere(GLUq, WIDTH_HANDS, SLICES, STACKS);
            gl.glTranslatef(-0.055f, 0.015f, 0.05f);
            glu.gluSphere(GLUq, WIDTH_FINGERS, SLICES, STACKS);
        }
        if (c == 'F'|| c == 'S') {
            AZUL(gl);
            gl.glTranslatef(0.79f, -0.15f, -0.05f);
            glu.gluSphere(GLUq, WIDTH_HANDS, SLICES, STACKS);
            gl.glTranslatef(-0.055f, 0.015f, 0.05f);
            glu.gluSphere(GLUq, WIDTH_FINGERS, SLICES, STACKS);
        }
        if (c == 'T') {
            AZUL(gl);
            gl.glTranslatef(0.6f, -0.6f, 0f);
            glu.gluSphere(GLUq, WIDTH_HANDS, SLICES, STACKS);
            gl.glTranslatef(-0.055f, 0.015f, 0.05f);
            glu.gluSphere(GLUq, WIDTH_FINGERS, SLICES, STACKS);
        }

        set_grey_material(gl);
        gl.glPopMatrix();
        //we create right arm
        gl.glPushMatrix();
        if (c == ' ' || c == 'E' || c == ' ') {
            gl.glTranslatef(0.55f, -0.3f, 0f);
            gl.glRotatef(90f, 1f, 0.20f, 0f);

            glu.gluCylinder(GLUq, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(GLUq, WIDTH_ARMS, SLICES, STACKS);
            gl.glRotatef(90f, -1f, -0.20f, 0f);
            gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);
            gl.glRotatef(90f, 1f, 0.20f, 0f);
        }
        if (c == 'F'|| c == 'S') {
            gl.glTranslatef(0.55f, -0.3f, 0f);
            gl.glRotatef(90f, 1f, 0.20f, 0f);
            gl.glRotatef(110f, 0f, 1f, 0f);
            glu.gluCylinder(GLUq, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
            glu.gluSphere(GLUq, WIDTH_ARMS, SLICES, STACKS);
            gl.glRotatef(90f, -1f, -0.20f, 0f);
            gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);
            gl.glRotatef(90f, 1f, 0.20f, 0f);
        }

        gl.glPopMatrix();
    }

    public void set_amarillo_material(GL gl) {
        float mat_ambient[] = {1f, 1f, 0f, 1.0f};
        float[] mat_diffuse = {0.0f, 0.0f, 0.0f, 0.0f};
        float mat_specular[] = {0.4f, 0.3f, 0.2f, 1.0f};
        float shine = 200f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_red_material(GL gl) {
        float[] mat_ambient = {1.0f, 0f, 0f, 0.0f};
        float[] mat_diffuse = {0.59f, 0.44f, 0.41f, 0.0f};
        float shine = 128f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void AZUL(GL gl) {

        float mat_ambient[] = {0f, 0f, 1f, 1.0f};
        float[] mat_diffuse = {0.0f, 0.0f, 0.0f, 0.0f};
        float mat_specular[] = {0.4f, 0.3f, 0.2f, 1.0f};
        float shine = 200f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void blanco(GL gl) {

        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void blancoCabeza(GL gl) {

        float mat_ambient[] = {0.7f, 0.7f, 0.7f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void verde(GL gl) {

        float mat_ambient[] = {0.7f, 1f, 0.7f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_grey_material(GL gl) {

        float mat_ambient[] = {0.58f, 0.58f, 0.58f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void negro(GL gl) {

        float mat_ambient[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_diffuse[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void azul(GL gl) {

        float mat_ambient[] = {0.0f, 0.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void box(GL gl) {
        gl.glBegin(GL.GL_POLYGON);/* f1: front */

        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f2: bottom */

        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f3:back */

        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f4: top */

        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f5: left */

        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f6: right */

        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glEnd();

    }

    public void fondo(GL gl, GLU glu, Texture t) {
        int m = t.getTextureObject();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, m);

        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2d(0.0f, 1.0f);
        gl.glVertex3d(-6.0f, -6.0f, -6.0f);

        gl.glTexCoord2d(1f, 1f);
        gl.glVertex3d(6.0f, -6.0f, -6.0f);

        gl.glTexCoord2d(1.0f, 0.0f);
        gl.glVertex3d(6.0f, 6.0f, -6.0f);

        gl.glTexCoord2d(0.0f, 0.0f);
        gl.glVertex3d(-6.0f, 6.0f, -6.0f);

        gl.glEnd();
        gl.glFlush();
        gl.glDisable(GL.GL_TEXTURE_2D);

    }

}
