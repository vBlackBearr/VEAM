/*
 *********************************************************
 ****   Elaborado por Luis Arturo Puentes Gomez   ********
 *********************************************************
 */
package GUI;

import Drawers.DrawEnder;
import Drawers.MuñecoNieve;
import Drawers.Polarin;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.*;
import com.sun.opengl.util.texture.TextureIO;
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
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class Canvas extends javax.swing.JFrame implements GLEventListener, MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {

    public static Clip clipFondo;
    public static int bnds = 0;
    public static char bndF = ' ';
    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private float zoom = .9f;
    private int oldMouseX;
    private int oldMouseY;
    boolean[] keys = new boolean[256]; //to know which key is pressed

    //position of stan in the window
    private static final float X_POSITION = 0f;
    private static final float Y_POSITION = -0.08f;
    private static final float Z_POSITION = 0f;

    private static float X_POSITION1 = 0f;
    private static float Y_POSITION1 = -0.08f;
    private static float Z_POSITION1 = 0f;

    static String caminar = "src/sonidos/pasos.wav";
    static String salto = "src/sonidos/salto.wav";
    static String disparos = "src/sonidos/disparos.wav";
    static String hola = "src/sonidos/hola.wav";
    static String fuerte = "src/sonidos/gruñido.wav";

    //layout(location=0) in ver3 vertPos;
    Clip clip;

    Texture tSnow, tFuego, tHola, tPiedras, tSaltar, actual;

    //Collision
    private static final float posX = 0f;
    private static final float posY = 0f;
    private static final float posZ = 0f;

    //mBallCentro Stan
    float[] centroMCabeza = {0, 1f, 0.5f, 0.0f};

    //posicion pelota
    float posPX = 1.5f;
    float posPY = 0.9f;
    float posPZ = 0f;


    private static final JMenuBar jMenuBar = new JMenuBar();

    private static final JMenu jMSonidoOn = new JMenu();

    private static final JMenu jMSonidoOff = new JMenu();

    private static final JMenu jMFunciones = new JMenu();

    private static final JMenu jMAcercaDe = new JMenu();

    static final GLCanvas canvas = new GLCanvas();

    

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Proyecto Enderman Animado");
        GLCanvas canvas = new GLCanvas();
        
        
        jMSonidoOn.setText("Sonido On");
        jMenuBar.add(jMSonidoOn);

        jMSonidoOff.setText("Sonido Off");
        jMenuBar.add(jMSonidoOff);

        jMFunciones.setText("Funciones");
        jMenuBar.add(jMFunciones);

        jMAcercaDe.setText("Acerca De");
        jMenuBar.add(jMAcercaDe);

        jMSonidoOn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMSonidoOnMouseClicked(evt);
            }
        });

        jMSonidoOff.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMSonidoOffMouseClicked(evt);
            }
        });

        jMFunciones.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMFuncionesMouseClicked(evt);
            }
        });

        jMAcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMAcercaDeMouseClicked(evt);
            }
        });

        frame.setJMenuBar(jMenuBar);
        canvas.addGLEventListener(new Canvas());
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
                0.9f, 0.9f, 0.9f, 0.9f);
        gl.glShadeModel(GL.GL_SMOOTH);

        try {
            File iCielo = new File("src/fondos/snow.jpg");
            tSnow = TextureIO.newTexture(iCielo, true);

            File iPiso = new File("src/fondos/fuego.jpg");
            tFuego = TextureIO.newTexture(iPiso, true);

            File iAtras = new File("src/fondos/hola.jpg");
            tHola = TextureIO.newTexture(iAtras, true);

            File iIzquierda = new File("src/fondos/piedras.jpg");
            tPiedras = TextureIO.newTexture(iIzquierda, true);

            File iDerecha = new File("src/fondos/Saltar.jpg");
            tSaltar = TextureIO.newTexture(iDerecha, true);

        } catch (IOException ex) {
            System.err.println("Error de carga de fondo: " + ex);
        }

        drawable.addMouseListener(
                this);
        drawable.addMouseMotionListener(
                this);
        drawable.addKeyListener(
                this);
        drawable.addMouseWheelListener(
                this);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private  void initComponents() {

        btnPlay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPlay.setText("PLAY");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(150, Short.MAX_VALUE)
                                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        //DrawEnder end2 = new DrawEnder();
        Polarin polar = new Polarin();
        MuñecoNieve muñeco = new MuñecoNieve();

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
        boolean[] acciones = {keys['1'], keys['2'], keys['3'], keys['4'], keys['5'],
            keys['6'], keys['7'], keys['8'], keys['9'], keys['0']};

        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 15.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

        int globalAmbient = GL.GL_LIGHT_MODEL_AMBIENT * GL.GL_MATERIAL_SIDE_HINT_PGI;
        int ambient = GL.GL_LIGHT0 * GL.GL_MATERIAL_SIDE_HINT_PGI;

        /*
        switch (bndF) {
            case '1':
                actual = tFuego;
                break;
            case '2':
                actual = tHola;
                break;
            case '3':
                actual = tPiedras;
                break;
            case '4':
                actual = tSaltar;
                break;
            default:
                actual = tSnow;
                break;
        }

        gl.glPushMatrix();
        gl.glScaled(10.0f, 10.0f, 0.5f);
        gl.glTranslatef(0.0f, 0.0f, -40f);
        end.fondo(gl, glu, actual);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(10.0f, 10.0f, 10.5f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(-90, 1.0f, 0.0f, 0.0f);
        end.fondo(gl, glu, actual);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(10.0f, 0.9f, 10.0f);
        gl.glTranslatef(0.0f, 20.0f, 0.0f);
        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
        end.fondo(gl, glu, actual);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(10.0f, 10.9f, 10.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
        end.fondo(gl, glu, actual);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(10.0f, 10.9f, 10.5f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
        end.fondo(gl, glu, actual);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(10.0f, 10.0f, 10.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glRotatef(-180, 1.0f, 0.0f, 0.0f);
        end.fondo(gl, glu, actual);
        gl.glPopMatrix();
        gl.glFlush();
//         Flush all drawing operations to the graphics card
        gl.glFlush();*/

        //efecto colicion
        gl.glPushMatrix();
        gl.glTranslatef(X_POSITION1, Y_POSITION1, Z_POSITION1);
        gl.glScaled(.5f, .5f, .5f);
        end.draw_end(gl, acciones);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 0.0f, 0.0f);
//        polar.dibujaPolarin(gl, false, false, false, false, false);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
//        muñeco.nieves(gl, false, false, false, false, false, false, false, false);
        gl.glPopMatrix();

        float[] centroP = {posPX, posPY, posPZ};

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
        switch (e.getKeyCode()) {
            case 'D':
                X_POSITION1 += .2;
                break;
            case 'A':
                X_POSITION1 -= .2;
                break;
            case 'W':
                Z_POSITION1 += .2;
                break;
            case 'S':
                Z_POSITION1 -= .2;
                break;
        }
        if (e.getKeyCode() < 250 && keys[e.getKeyCode()] == false) {

            if (e.getKeyCode() == '1'
                    || e.getKeyCode() == '2'
                    || e.getKeyCode() == '3'
                    || e.getKeyCode() == '4'
                    || e.getKeyCode() == '5') {
                keys['1'] = false;
                keys['2'] = false;
                keys['3'] = false;
                keys['4'] = false;
                keys['5'] = false;
            }

            if (e.getKeyCode() == '6'
                    || e.getKeyCode() == '7'
                    || e.getKeyCode() == '8'
                    || e.getKeyCode() == '9'
                    || e.getKeyCode() == '0') {
                keys['6'] = false;
                keys['7'] = false;
                keys['8'] = false;
                keys['9'] = false;
                keys['0'] = false;
            }

            if (e.getKeyCode() == 'Z'
                    || e.getKeyCode() == 'X'
                    || e.getKeyCode() == 'C'
                    || e.getKeyCode() == 'V'
                    || e.getKeyCode() == 'B') {
                keys['Z'] = false;
                keys['X'] = false;
                keys['C'] = false;
                keys['V'] = false;
                keys['B'] = false;
            }
            keys['E'] = false;
            keys['R'] = false;
            keys[e.getKeyCode()] = true;

            switch (e.getKeyCode()) {
                case '1':
                    reproducir(caminar);
                    break;
                case '2':
                    reproducir(salto);
                    break;
                case '3':
                    reproducir(disparos);
                    break;
                case '4':
                    reproducir(hola);
                    break;
                case '5':
                    reproducir(fuerte);
                    break;
                case 'Z':
                    bndF = '1';

                case 'X':
                    bndF = '2';
                    break;
                case 'C':
                    bndF = '3';
                    break;
                case 'V':
                    bndF = '4';
                    break;
                case 'B':
                    bndF = '5';
                    break;
            }
        } else {
            keys[e.getKeyCode()] = false;
        }
    }

    public void reproducir(String efecto) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(efecto)));
            clip.start();
        } catch (IOException e) {

            System.err.println("Error de carga de sonido: " + e);
        } catch (LineUnavailableException e) {
            System.err.println("Error de carga de sonido: " + e);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Error de carga de sonido: " + e);
        }
    }

    public static void jMSonidoOnMouseClicked(MouseEvent evt) {
        if (bnds == 0) {
            sonidoFondo();
            bnds = 1;
        } else {
            clipFondo.start();
        }
        canvas.repaint();
    }

    public static void jMSonidoOffMouseClicked(MouseEvent evt) {
        clipFondo.stop();
        canvas.repaint();
    }

    public static void jMFuncionesMouseClicked(MouseEvent evt) {
        JOptionPane.showMessageDialog(null, "          FUNCIONES: \n"
                + "         **Se desplaza la vista con el mouse\n"
                + "         **Se hace zoom in, zoom out con la rueda del raton\n"
                + "  Acciones:                     Gestos:\n"
                + "    1)Caminar                       6)Feliz\n"
                + "    2)Saltar                          7)Triste\n"
                + "    3)Disparar                      8)Sexy\n"
                + "    4)Saludar                       9)Enojado\n"
                + "    5)Fuerte                         0)Sorprendido\n"
                + "\n"
                + "   Movimiento hacia enfrente, atras, izquierda, derecha\n"
                + "   con las teclas:     W \n"
                + "                                A S D\n\n"
                + "    Fondos diferentes con las teclas:\n"
                + "            Z,X,C,V,B");
    }

    public static void jMAcercaDeMouseClicked(MouseEvent evt) {
        JOptionPane.showMessageDialog(null, "INSTITUTO TECNOLOGIGO DE TOLUCA\n"
                + "Desarrollado por: Luis Arturo Puentes Gomez\n"
                + "Profesora: Rocío Elizabeth Pulido Alba");
    }

    public static synchronized void sonidoFondo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File d = new File("src/sonidos/Musica.wav");
                    clipFondo = AudioSystem.getClip();
                    AudioInputStream inputstream = AudioSystem.getAudioInputStream(d);
                    clipFondo.open(inputstream);
                    clipFondo.start();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                } catch (LineUnavailableException e) {
                    System.err.println(e.getMessage());
                } catch (UnsupportedAudioFileException e) {
                    System.err.println(e.getMessage());
                }

            }
        }).start();
    }

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private javax.swing.JButton btnPlay;
    
}
