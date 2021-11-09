package Drawers;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class DrawEnder {

    //precision and global variables
    private static final int SLICES = 40;
    private static final int STACKS = 40;
    private GLUquadric q = null;
    private static int mvt = 0;

    //position of each component int the window
    public DrawEnder() {
    }

    public void draw_pelota(GL gl, GLU glu){
        set_eyes_material1(gl);
        gl.glPushMatrix();
        glu.gluSphere(q,0.2, SLICES, STACKS);
        gl.glPopMatrix();
    }
    
    public void draw_end(GL gl, boolean[] acciones) {
//boolean walk, boolean jump, boolean shooting
        GLU glu = new GLU();
        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);

        //Enderman caminando 1
        if (acciones[0] && mvt % 20 + 10 > 20) {
            gl.glTranslatef(0f, 0.35f, 0f);
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, 'F', false);
            draw_leg_left(gl, glu, 'B', true);
            draw_arm_left(gl, glu, 'F');
            draw_arm_right(gl, glu, 'B');

        } //Enderman caminando 2
        else if (acciones[0] && mvt % 20 + 10 <= 20) {

            gl.glTranslatef(0f, 0.35f, 0f);
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, 'B', false);
            draw_leg_left(gl, glu, 'F', true);
            draw_arm_left(gl, glu, 'B');
            draw_arm_right(gl, glu, 'F');
            gl.glFlush();

        }//Enderman Saltando 1
        else if (acciones[1] && mvt % 20 + 10 > 20) {
            gl.glTranslatef(0f, 0.35f, 0f);
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, 'J', false);
            draw_leg_left(gl, glu, 'J', true);
            draw_arm_left(gl, glu, 'U');
            draw_arm_right(gl, glu, 'U');
        } //Enderman Saltando 2
        else if (acciones[1] && mvt % 20 + 10 <= 20) {
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, 'J', false);
            draw_leg_left(gl, glu, 'J', true);
            draw_arm_left(gl, glu, 'U');
            draw_arm_right(gl, glu, 'U');
        } //Enderman Disparando
        else if (acciones[2]) {
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, ' ', false);
            draw_leg_left(gl, glu, ' ', true);
            draw_arm_left(gl, glu, 'S');
            draw_arm_right(gl, glu, 'S');
            draw_gun(gl, glu);
        } //Enderman Saludando 1
        else if (acciones[3] && mvt % 20 + 10 > 20) {
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, ' ', false);
            draw_leg_left(gl, glu, ' ', true);
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, 'H');
        } //Enderman Saludando 2
        else if (acciones[3] && mvt % 20 + 10 <= 20) {
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, ' ', false);
            draw_leg_left(gl, glu, ' ', true);
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, 'h');

        }//Strong
        else if (acciones[4]) {
            draw_head(gl, glu, 'G');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, ' ', false);
            draw_leg_left(gl, glu, ' ', true);
            draw_arm_left(gl, glu, 'R');
            draw_arm_right(gl, glu, 'R');
        }//Original 
        else {
            draw_head(gl, glu, ' ');
            draw_body(gl, glu);
            draw_hat(gl, glu);
            draw_leg_right(gl, glu, ' ', false);
            draw_leg_left(gl, glu, ' ', true);
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');

        }

        if (acciones[4]) {
            gl.glPushMatrix();
            gl.glRotatef(60, 0, 1, 0);
            gl.glTranslatef(-.9f, 0, .2f);

        }
        if (acciones[5]) {
            draw_gestos(gl, glu, 'H');
        } else if (acciones[6]) {
            draw_gestos(gl, glu, 'S');
        } else if (acciones[7]) {
            draw_gestos(gl, glu, 'Q');
        } else if (acciones[8]) {
            draw_gestos(gl, glu, 'A');
        } else if (acciones[9]) {
            draw_gestos(gl, glu, 'O');
        } else {
            draw_gestos(gl, glu, 'W');
        }

        if (acciones[4]) {
            gl.glPopMatrix();

        }
        mvt++;

    }

    //cuerpo
    public void draw_body(GL gl, GLU glu) {
        set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0f, 0.4f);
        gl.glScalef(1.6f, 2.5f, 0.8f);
        box(gl);
        gl.glPopMatrix();

    }

    public void draw_head(GL gl, GLU glu, char c) {
        set_black_material(gl);
        gl.glPushMatrix();
        if (c == 'G') {
            gl.glRotatef(60, 0, 1, 0);
            gl.glTranslatef(-.9f, 0, .2f);

        }
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 2.5f, 0.0f);
        gl.glScalef(1.6f, 1.6f, 1.6f);
        box(gl);
        gl.glPopMatrix();

        //left eye
        this.set_eyes_material1(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.001f, 3.2f, 1.401f);
        gl.glScalef(0.2f, 0.2f, 0.2f);
        box(gl);
        gl.glPopMatrix();

        this.set_eyes_material2(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.201f, 3.2f, 1.401f);
        gl.glScalef(0.2f, 0.2f, 0.2f);
        box(gl);
        gl.glPopMatrix();

        this.set_eyes_material1(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.401f, 3.2f, 1.401f);
        gl.glScalef(0.2f, 0.2f, 0.2f);
        box(gl);
        gl.glPopMatrix();

        //right eye
        this.set_eyes_material1(gl);
        gl.glPushMatrix();
        gl.glTranslatef(.999f, 3.2f, 1.401f);
        gl.glScalef(0.2f, 0.2f, 0.2f);
        box(gl);
        gl.glPopMatrix();

        this.set_eyes_material2(gl);
        gl.glPushMatrix();
        gl.glTranslatef(1.199f, 3.2f, 1.401f);
        gl.glScalef(0.2f, 0.2f, 0.2f);
        box(gl);
        gl.glPopMatrix();

        this.set_eyes_material1(gl);
        gl.glPushMatrix();
        gl.glTranslatef(1.399f, 3.2f, 1.401f);
        gl.glScalef(0.2f, 0.2f, 0.2f);
        box(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    public void draw_gestos(GL gl, GLU glu, char c) {
        this.set_eyes_material1(gl);
        switch (c) {
            case 'H'://Happy
                gl.glPushMatrix();
                gl.glTranslatef(.5f, 2.7f, 1.41f);
                gl.glScaled(.5, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(1.0f, 2.7f, 1.41f);
                gl.glRotatef(45, 0, 0, 1);
                gl.glScaled(.2, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(0.35f, 2.85f, 1.41f);
                gl.glRotatef(-45, 0, 0, 1);
                gl.glScaled(.2, .1, .2);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'S':
                gl.glPushMatrix();
                gl.glTranslatef(.5f, 2.7f, 1.41f);
                gl.glScaled(.5, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(0.45f, 2.585f, 1.41f);
                gl.glRotatef(45, 0, 0, 1);
                gl.glScaled(.2, .11, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(0.938f, 2.723f, 1.41f);
                gl.glRotatef(-45, 0, 0, 1);
                gl.glScaled(.2, .11, .2);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'Q':
                gl.glPushMatrix();
                gl.glTranslatef(.5f, 2.7f, 1.41f);
                gl.glScaled(.5, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(1.0f, 2.7f, 1.41f);
                gl.glRotatef(45, 0, 0, 1);
                gl.glScaled(.2, .1, .2);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'A':
                gl.glPushMatrix();
                gl.glTranslatef(.5f, 2.7f, 1.41f);
                gl.glScaled(.5, .1, .2);
                box(gl);
                gl.glPopMatrix();

                set_red_material(gl);
                gl.glPushMatrix();
                gl.glTranslatef(1.0f, 3.3f, 1.41f);
                gl.glRotatef(45, 0, 0, 1);
                gl.glScaled(.4, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(0.35f, 3.6f, 1.41f);
                gl.glRotatef(-45, 0, 0, 1);
                gl.glScaled(.4, .1, .2);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'O':
                gl.glPushMatrix();
                gl.glTranslatef(.6f, 2.55f, 1.41f);
                gl.glScaled(.3, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(0.6f, 2.585f, 1.41f);
                gl.glRotatef(90, 0, 0, 1);
                gl.glScaled(.3, .11, .2);
                box(gl);
                gl.glPopMatrix();
                
                gl.glPushMatrix();
                gl.glTranslatef(.6f, 2.9f, 1.41f);
                gl.glScaled(.3, .1, .2);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(1.0f, 2.585f, 1.41f);
                gl.glRotatef(90, 0, 0, 1);
                gl.glScaled(.3, .11, .2);
                box(gl);
                gl.glPopMatrix();
                break;
        }
    }

    public void draw_hat(GL gl, GLU glu) {
        set_red_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(.8f, 4.1f, 0.8f);
        gl.glRotatef(90, -1, 0, 0);
        glu.gluCylinder(q, 1.0, 0.0, 2.5, 15, 15);
        gl.glPopMatrix();

        set_white_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(.8f, 6.5f, 0.8f);
        gl.glScalef(2.5f, 2.5f, 2.5f);
        glu.gluSphere(q, 0.1f, SLICES, STACKS);
        gl.glPopMatrix();
    }

    //pierna izquierda
    public void draw_leg_left(GL gl, GLU glu, char c, boolean left) {
        set_black_material(gl);
        gl.glPushMatrix();
        switch (c) {

            case 'F'://Walking Front
                gl.glRotatef(30f, -1, 0, 0);
                gl.glTranslatef(0.9f, -6.0f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                break;
            case 'B'://Walking back
                gl.glRotatef(30f, 1, 0, 0);
                gl.glTranslatef(0.9f, -5.5f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);

                break;
            default:// Original

                gl.glTranslatef(0.9f, -6.0f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);

                break;
        }
        gl.glPopMatrix();
    }

    //pierna derecha
    public void draw_leg_right(GL gl, GLU glu, char c, boolean left) {
        set_black_material(gl);
        gl.glPushMatrix();
        switch (c) {
            case 'F'://Walking front
                gl.glPushMatrix();
                gl.glRotatef(30f, -1, 0, 0);
                gl.glTranslatef(0.2f, -6.0f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'B'://Walking back
                gl.glPushMatrix();
                gl.glRotatef(30f, 1, 0, 0);
                gl.glTranslatef(0.2f, -5.5f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            default://Original
                gl.glPushMatrix();
                gl.glTranslatef(0.2f, -6.0f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
        }
        gl.glPopMatrix();
    }

    //brazo izquierdo
    public void draw_arm_left(GL gl, GLU glu, char c) {
        set_black_material(gl);
        switch (c) {
            case 'F'://Walking front
                gl.glPushMatrix();
                gl.glRotatef(30f, -1, 0, 0);
                gl.glTranslatef(1.6f, -4.2f, 1.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'B'://Walking back
                gl.glPushMatrix();
                gl.glRotatef(30f, 1, 0, 0);
                gl.glTranslatef(1.6f, -3.4f, -.5f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'U'://Jumping
                gl.glPushMatrix();
                gl.glTranslatef(4.8f, 7.2f, 0.6f);
                gl.glRotatef(150f, 0, 0, 1);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'S'://Shooting
                gl.glPushMatrix();
                gl.glRotatef(30f, -1, 0, 0);
                gl.glTranslatef(1.6f, -1.2f, 1.7f);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glRotatef(90f, -1, 0, 0);
                gl.glTranslatef(1.6f, -5.0f, -0.2f);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            default://Original
                gl.glPushMatrix();
                gl.glRotatef(5f, 0, 0, 1);
                gl.glTranslatef(1.8f, -3.7f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'R':
                gl.glPushMatrix();
                gl.glTranslatef(4.5f, 2.0f, 0.6f);
                gl.glRotatef(90f, 0, 0, 1);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(4.5f, 2.0f, 0.6f);
                gl.glRotatef(30f, 0, 0, 1);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
        }

    }

    //brazo derecho
    public void draw_arm_right(GL gl, GLU glu, char c) {
        switch (c) {
            case 'F'://Walking front
                gl.glPushMatrix();
                gl.glRotatef(30f, -1, 0, 0);
                gl.glTranslatef(-0.4f, -4.2f, 1.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'B'://Walking back
                gl.glPushMatrix();
                gl.glRotatef(30f, 1, 0, 0);
                gl.glTranslatef(-0.4f, -3.4f, -.5f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'U'://Jumping
                gl.glPushMatrix();
                gl.glTranslatef(-2.8f, 7.0f, 0.6f);
                gl.glRotatef(150f, 0, 0, -1);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'S'://Shooting
                gl.glPushMatrix();
                gl.glRotatef(10f, 0, 0, -1);
                gl.glTranslatef(-0.8f, -0.5f, 0.6f);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glRotatef(30f, 0, 1, 0);
                gl.glRotatef(90f, -1, 0, 0);
                gl.glTranslatef(-1.2f, -3.5f, -0.2f);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'H'://Salute 1
                gl.glPushMatrix();
                gl.glRotatef(10f, 0, 0, -1);
                gl.glTranslatef(-0.8f, -0.5f, 0.6f);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                gl.glPushMatrix();

                gl.glTranslatef(0.2f, 1.2f, 2.9f);
                gl.glRotatef(30f, 0, 1, 0);
                gl.glRotatef(130f, -1, 0, 0);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'h'://Salute 2
                gl.glPushMatrix();
                gl.glRotatef(10f, 0, 0, -1);
                gl.glTranslatef(-0.8f, -0.5f, 0.6f);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                gl.glPushMatrix();

                gl.glTranslatef(-2f, 1.5f, 2.9f);
                gl.glRotatef(-30f, 0, 1, 0);
                gl.glRotatef(130f, -1, 0, 0);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            default://Original
                gl.glPushMatrix();
                gl.glRotatef(5f, 0, 0, -1);
                gl.glTranslatef(-0.6f, -3.55f, 0.6f);
                gl.glScalef(0.4f, 6.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
            case 'R':
                gl.glPushMatrix();
                gl.glTranslatef(-0.0f, 2.0f, 0.6f);
                gl.glRotatef(90f, 0, 0, 1);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslatef(-3.2f, 2.15f, 0.6f);
                gl.glRotatef(30f, 0, 0, -1);
                gl.glScalef(0.4f, 3.0f, 0.4f);
                box(gl);
                gl.glPopMatrix();
                break;
        }
    }

    public void draw_gun(GL gl, GLU glu) {
        set_grey_material(gl);
        gl.glPushMatrix();

        gl.glTranslatef(0.7f, .4f, 3.4f);
        gl.glScaled(0.1f, 0.1f, 0.1f);
        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, 2, 2, 5, 10, 10);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(30f, 0, 1, 0);
        gl.glRotatef(90f, -1, 0, 0);
        gl.glTranslatef(-1.2f, -6.2f, .4f);
        gl.glScalef(0.4f, 3.0f, 0.4f);
        box(gl);
        gl.glPopMatrix();

    }

    public void set_eyes_material1(GL gl) {

        float mat_ambient[] = {.224f, .121f, .250f, 1.0f};
        float mat_diffuse[] = {.224f, .121f, .250f, 1.0f};
        float mat_specular[] = {0.224f, 0.121f, 0.250f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_eyes_material2(GL gl) {

        float mat_ambient[] = {.204f, 0f, .250f, 1.0f};
        float mat_diffuse[] = {.204f, 0f, .250f, 1.0f};
        float mat_specular[] = {0.204f, 0.0f, 0.250f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
    }

    public void set_black_material(GL gl) {

        float mat_ambient[] = {0.1f, 0.1f, 0.1f, 0.2f};
        float mat_diffuse[] = {0.1f, 0.1f, 0.1f, 0.2f};
        float mat_specular[] = {0f, 0f, 0f, 1.0f};
        float shine = 15.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_white_material(GL gl) {

        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.1f, 0.1f, 0.1f, 0.1f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_grey_material(GL gl) {

        float mat_ambient[] = {0.7f, 0.7f, 0.7f, 1.0f};
        float mat_diffuse[] = {0.7f, 0.7f, 0.7f, 1.0f};
        float mat_specular[] = {0.9f, 0.9f, 0.9f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_red_material(GL gl) {

        float[] mat_ambient = {0.8f, 0.05f, 0.15f, 0.2f};
        float[] mat_diffuse = {0.4f, 0.4f, 0.4f, 1.0f};
        float[] mat_specular = {0.5f, 0.5f, 0.5f, 0.1f};
        float shine = 15.0f;

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

        gl.glTexCoord2d(1.0f, 1.0f);
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
