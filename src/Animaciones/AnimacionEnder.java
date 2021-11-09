/*
 *********************************************************
 ****   Elaborado por Luis Arturo Puentes Gomez   ********
 *********************************************************
 */
package Animaciones;

import Drawers.DrawEnder;
import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AnimacionEnder extends javax.swing.JFrame implements GLEventListener, MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {

    /**
     * @return the y
     */
    public int getGiro() {
        return giro;
    }

    /**
     * @param giro the y to set
     */
    public void setGiro(int giro) {
        this.giro = giro;
    }

    private int giro;

    public static char bndF = ' ';
    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private float zoom = 1;
    private int oldMouseX;
    private int oldMouseY;

    //position of stan in the window
    private static final float X_POSITION = 0f;
    private static final float Y_POSITION = -0.08f;
    private static final float Z_POSITION = 0f;

    private boolean[] animaciones;

    private boolean bndAnimacion = false;
    private boolean bndLadoGiro = false;

    //Collision
    private static final float posX = 0;
    private static final float posY = 0;
    private static final float posZ = 0;

    //posicion pelota
    float posPX = 1.5f;
    float posPY = 0.9f;
    float posPZ = 0f;

    static final GLCanvas canvas = new GLCanvas();

    public AnimacionEnder() {
        setBndLadoGiro(true);

        animaciones = new boolean[10];

        for (int i = 0; i < this.animaciones.length; i++) {
            this.animaciones[i] = false;
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Proyecto Enderman Animado");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new AnimacionEnder());
        frame.add(canvas);
        frame.setSize(1000, 800);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();

    }

    @Override
    public void init(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);

        // set up lighting
        float light_ambient[] = {0.9f, 0.9f, 0.9f, 1.0f};
        float light_diffuse[] = {0.3f, 0.3f, 0.3f, 1.0f};
        float light_specular[] = {0.5f, 0.5f, 1.0f, 1.0f};
        float light_position[] = {0.0f, 0.5f, 1.0f, 0.0f};

        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);

        // Setup the drawing area and shading mode
        gl.glClearColor(
                0.03f, .543f, .057f, .2f);
        gl.glShadeModel(GL.GL_SMOOTH);

        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        drawable.addMouseWheelListener(this);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(150, Short.MAX_VALUE)
                                .addGap(107, 107, 107))
        );

        pack();
    }// </editor-fold>                 

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 300.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        DrawEnder end = new DrawEnder();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);

        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        glu.gluLookAt(0.1f, 0.0f, 20.0f,// eye
                0.0f, 0.0f, 0.0f, // looking at
                0.0f, 0.0f, 1.0f // is up
        );

        // Move the whole scene
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
        gl.glScaled(zoom, zoom, zoom);
        //gl.glTranslatef(0.0f, 0.0f, -zoom);

        //Start draw
        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 15.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

        gl.glPushMatrix();
        gl.glTranslatef(-1.5f, 0, 0);
        gl.glRotated(getGiro(), 0, 1, 0); // giro
        end.draw_end(gl, animaciones);
        gl.glPopMatrix();

        this.checkAnimacion();

    }

    public void setAnimaciones(boolean[] animaciones) {
        try {
            System.arraycopy(animaciones, 0, this.animaciones, 0, animaciones.length);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }

    public void setAnimaciones(boolean animaciones) {
        if (animaciones) {
            for (int i = 0; i < 5; i++) {
                this.animaciones[i] = true;
            }
        } else {
            for (int i = 0; i < 5; i++) {
                this.animaciones[i] = false;
            }
        }
    }

    public void setGestos(boolean animaciones) {
        if (animaciones) {
            for (int i = 5; i < this.animaciones.length; i++) {
                this.animaciones[i] = true;
            }
        } else {
            for (int i = 5; i < this.animaciones.length; i++) {
                this.animaciones[i] = false;
            }
        }
    }

    public void checkAnimacion() {
        if (isBndAnimacion()) {
            if (getGiro() >= 70) {
                setBndLadoGiro(false);
            }
            if (getGiro() <= -70) {
                setBndLadoGiro(true);
            }
            if (isBndLadoGiro()) {
                setGiro(getGiro() + 2);
            } else {
                setGiro(getGiro() - 2);
            }
        } else {
            setGiro(0);
        }
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaY = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        if (mwe.getWheelRotation() > 0) {
            zoom += .1;
        } else {
            zoom -= .1;
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void keyPressed(KeyEvent e) {

    }

    public static void jMAcercaDeMouseClicked(MouseEvent evt) {
        JOptionPane.showMessageDialog(null, "INSTITUTO TECNOLOGIGO DE TOLUCA\n"
                + "Desarrollado por: Luis Arturo Puentes Gomez\n"
                + "Profesora: Rocío Elizabeth Pulido Alba");
    }

    /**
     * @return the bndAnimacion
     */
    public boolean isBndAnimacion() {
        return bndAnimacion;
    }

    /**
     * @param bndAnimacion the bndAnimacion to set
     */
    public void setBndAnimacion(boolean bndAnimacion) {
        this.bndAnimacion = bndAnimacion;
    }

    public boolean isBndLadoGiro() {
        return bndLadoGiro;
    }

    /**
     * @param bndLadoGiro bndAnimacion to set
     */
    public void setBndLadoGiro(boolean bndLadoGiro) {
        this.bndLadoGiro = bndLadoGiro;
    }

}
