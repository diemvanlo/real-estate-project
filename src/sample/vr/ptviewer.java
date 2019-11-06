//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.applet.Applet;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class ptviewer extends Applet implements Runnable {
    static final boolean debug = false;
    static final double HFOV_MIN = 10.5D;
    static final double HFOV_MAX = 165.0D;
    static final long TIME_PER_FRAME = 10L;
    static final long ETERNITY = 100000000L;
    int quality = 3;
    boolean inited = false;
    Color bgcolor = null;
    long waittime = 0L;
    boolean WaitDisplayed = false;
    Image view = null;
    Image dwait = null;
    Image frame = null;
    Image offImage = null;
    Graphics offGraphics = null;
    int offwidth = 0;
    int offheight = 0;
    MemoryImageSource source = null;
    int awidth = 320;
    int aheight = 200;
    public int vwidth = 0;
    public int vheight = 0;
    boolean vset = false;
    int vx = 0;
    int vy = 0;
    int pwidth = 0;
    int pheight = 0;
    int[] vdata = null;
    byte[] hs_vdata = null;
    int[][] pdata = null;
    boolean show_pdata = true;
    boolean ready = false;
    boolean hsready = false;
    boolean PanoIsLoaded = false;
    boolean fatal = false;
    boolean mouseInWindow = true;
    boolean mouseInViewer = true;
    boolean panning = false;
    public boolean dirty = true;
    boolean showhs = false;
    boolean showCoordinates = false;
    int oldx = 0;
    int oldy = 0;
    int newx = 0;
    int newy = 0;
    int ptcursor = 0;
    public double yaw = 0.0D;
    public double hfov = 70.0D;
    public double hfov_min = 10.5D;
    public double hfov_max = 165.0D;
    public double pitch = 0.0D;
    public double pitch_max = 90.0D;
    public double pitch_min = -90.0D;
    public double yaw_max = 180.0D;
    public double yaw_min = -180.0D;
    double MASS = 0.0D;
    double oldspeedx = 0.0D;
    double oldspeedy = 0.0D;
    double autopan = 0.0D;
    double autotilt = 0.0D;
    double zoom = 1.0D;
    public double pan_steps = 20.0D;
    String filename = null;
    String inits = null;
    String MouseOverHS = null;
    String GetView = null;
    int click_x = -1;
    int click_y = -1;
    long frames = 0L;
    long lastframe = 0L;
    long ptimer = 0L;
    Thread loadPano = null;
    Thread ptviewerScript = null;
    String PTScript = null;
    String PTViewer_Properties = null;
    boolean loadAllRoi = true;
    int CurrentPano = -1;
    Hashtable sender = null;
    Thread preloadthread = null;
    String preload = null;
    String order = null;
    boolean antialias = false;
    Vector scaledPanos = null;
    double max_oversampling = 1.5D;
    int im_maxarray = 524288;
    int grid_bgcolor = 16777215;
    int grid_fgcolor = 0;
    Hashtable file_Cache = null;
    boolean file_cachefiles = true;
    Color pb_color;
    int pb_x;
    int pb_y;
    int pb_width;
    int pb_height;
    int[] percent;
    int numshs;
    int curshs;
    int[] shs_x1;
    int[] shs_x2;
    int[] shs_y1;
    int[] shs_y2;
    String[] shs_url;
    String[] shs_target;
    Object[] shs_him;
    boolean[] shs_active;
    int[] shs_imode;
    Vector shotspots;
    int[] atan_LU_HR;
    int[] sqrt_LU;
    double[] atan_LU;
    int PV_atan0_HR;
    int PV_pi_HR;
    static final int NATAN = 4096;
    static final int NSQRT = 4096;
    private double[][] f1;
    private int[][] f2;
    int numroi;
    String[] roi_im;
    int[] roi_xp;
    int[] roi_yp;
    boolean[] roi_loaded;
    Vector sounds;
    Hashtable applets;
    Vector app_properties;
    Vector hotspots;
    int numhs;
    int curhs;
    Object hs_image;
    double[] hs_xp;
    double[] hs_yp;
    double[] hs_up;
    double[] hs_vp;
    int[] hs_xv;
    int[] hs_yv;
    Color[] hs_hc;
    String[] hs_name;
    String[] hs_url;
    String[] hs_target;
    Object[] hs_him;
    String[] hs_mask;
    boolean[] hs_visible;
    int[] hs_imode;
    int[] hs_link;
    static final double NO_UV = -200.0D;
    static final int HSIZE = 12;
    static final int IMODE_NORMAL = 0;
    static final int IMODE_POPUP = 1;
    static final int IMODE_ALWAYS = 2;
    static final int IMODE_WARP = 4;
    static final int IMODE_WHS = 8;
    static final int IMODE_TEXT = 16;
    static Class class$java$lang$String;
    static Class class$java$applet$Applet;

    public ptviewer() {
        this.pb_color = Color.gray;
        this.pb_x = -1;
        this.pb_y = -1;
        this.pb_width = -1;
        this.pb_height = 10;
        this.percent = null;
        this.numshs = 0;
        this.curshs = -1;
        this.shotspots = null;
        this.atan_LU_HR = null;
        this.atan_LU = null;
        this.PV_atan0_HR = 0;
        this.PV_pi_HR = 0;
        this.numroi = 0;
        this.sounds = null;
        this.applets = null;
        this.app_properties = null;
        this.hotspots = null;
        this.numhs = 0;
        this.curhs = -1;
        this.hs_image = null;
    }

    public ptviewer(int[][] var1) {
        this.pb_color = Color.gray;
        this.pb_x = -1;
        this.pb_y = -1;
        this.pb_width = -1;
        this.pb_height = 10;
        this.percent = null;
        this.numshs = 0;
        this.curshs = -1;
        this.shotspots = null;
        this.atan_LU_HR = null;
        this.atan_LU = null;
        this.PV_atan0_HR = 0;
        this.PV_pi_HR = 0;
        this.numroi = 0;
        this.sounds = null;
        this.applets = null;
        this.app_properties = null;
        this.hotspots = null;
        this.numhs = 0;
        this.curhs = -1;
        this.hs_image = null;
        this.pdata = var1;
        this.PanoIsLoaded = true;
        this.math_setLookUp(this.pdata);
        this.filename = "Pano";
    }

    void initialize() {
        this.numhs = 0;
        this.curhs = -1;
        this.curshs = -1;
        this.numroi = 0;
        this.loadAllRoi = true;
        this.yaw = 0.0D;
        this.hfov = 70.0D;
        this.hfov_min = 10.5D;
        this.hfov_max = 165.0D;
        this.pitch = 0.0D;
        this.pitch_max = 90.0D;
        this.pitch_min = -90.0D;
        this.yaw_max = 180.0D;
        this.yaw_min = -180.0D;
        this.autopan = 0.0D;
        this.autotilt = 0.0D;
        this.zoom = 1.0D;
        this.pwidth = 0;
        this.pheight = 0;
        this.stopPan();
        this.lastframe = 0L;
        this.dirty = true;
        this.showhs = false;
        this.showCoordinates = false;
        this.MouseOverHS = null;
        this.GetView = null;
        this.WaitDisplayed = false;
        this.pan_steps = 20.0D;
        this.order = null;
    }

    public void init() {
        this.fatal = false;
        this.preloadthread = null;
        this.preload = null;
        this.ptcursor = 0;
        this.file_init();
        this.math_init();
        this.pb_init();
        this.app_init();
        this.snd_init();
        this.shs_init();
        this.hs_init();
        this.sender = new Hashtable();
        this.inited = true;
        this.repaint();
        byte[] var1;
        if ((var1 = this.file_read("PTDefault.html", (int[]) null)) != null) {
            this.PTViewer_Properties = new String(var1);
        }

        this.initialize();
        if (this.PTViewer_Properties != null) {
            this.ReadParameters(this.PTViewer_Properties);
        }

        this.ReadParameters((String) null);
        if (this.filename != null && this.filename.startsWith("ptviewer:")) {
            int var2 = Integer.parseInt(this.filename.substring(this.filename.indexOf(58) + 1));
            if (this.myGetParameter((String) null, "pano" + var2) != null) {
                this.filename = null;
                this.ReadParameters(this.myGetParameter((String) null, "pano" + var2));
            }
        }

    }

    public String getAppletInfo() {
        return "PTViewer v. 2.5  © H. Dersch, der@fh-furtwangen.de";
    }

    public void start() {
        if (this.loadPano == null) {
            this.loadPano = new Thread(this);
            this.loadPano.start();
        }

    }

    public synchronized void stop() {
        this.stopThread(this.preloadthread);
        this.preloadthread = null;
        this.stopThread(this.loadPano);
        this.loadPano = null;
        this.stopAutoPan();
        this.stopPan();
        this.stopApplets(0);
        this.ready = false;
        this.hsready = false;
        this.vdata = null;
        this.hs_vdata = null;
        this.view = null;
        if (!this.vset) {
            this.vwidth = 0;
            this.vheight = 0;
        }

        this.offImage = null;
        this.scaledPanos = null;
    }

    synchronized void PV_reset() {
        this.ready = false;
        this.hsready = false;
        this.hs_dispose();
        this.roi_dispose();
        this.PanoIsLoaded = false;
        this.filename = null;
        this.MouseOverHS = null;
        this.GetView = null;
        this.pb_reset();
        this.inits = null;
        this.order = null;
        System.gc();
    }

    public synchronized void destroy() {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = null;
        this.PV_reset();
        if (this.sender != null) {
            this.sender.clear();
            this.sender = null;
        }

        this.vdata = null;
        this.hs_vdata = null;
        this.source = null;
        this.frame = null;
        this.view = null;
        this.dwait = null;
        this.pdata = null;
        this.math_dispose();
        this.shs_dispose();
        this.snd_dispose();
        System.gc();
    }

    public void run() {
        if (Thread.currentThread() == this.preloadthread && this.preload != null) {
            int var8;
            if ((var8 = this.getNumArgs(this.preload, ',')) > 0) {
                for (int var2 = 0; var2 < var8; ++var2) {
                    String var3;
                    if ((var3 = this.getArg(var2, this.preload, ',')) != null && this.file_cachefiles && this.file_Cache != null && this.file_Cache.get(var3) == null && var3 != this.filename) {
                        this.file_read(var3, (int[]) null);
                    }
                }
            }

        } else if (Thread.currentThread() == this.ptviewerScript) {
            if (this.PTScript != null) {
                this.PTViewerScript(this.PTScript);
            }

        } else {
            this.ResetCursor();
            if (!this.PanoIsLoaded) {
                this.show_pdata = true;
                if (this.filename == null) {
                    if (this.pwidth != 0) {
                        this.filename = "_PT_Grid";
                    } else {
                        this.show_pdata = false;
                    }
                }

                if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    this.pdata = this.im_loadPano((String) null, this.pdata, this.pwidth, this.pheight);
                } else {
                    this.pdata = this.im_loadPano(this.filename, this.pdata, this.pwidth, this.pheight);
                }

                System.gc();
            }

            if (this.pdata == null) {
                this.fatal = true;
                this.repaint();
            } else {
                if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    try {
                        String var1 = " {file=" + this.filename + "} ";
                        if (this.order != null) {
                            var1 = var1 + "{order=" + this.order + "} ";
                        }

                        if (this.antialias) {
                            var1 = var1 + "{antialias=true} ";
                            var1 = var1 + "{oversampling=" + this.max_oversampling + "} ";
                        }

                        Applet var4;
                        (var4 = (Applet) Class.forName("ptmviewer").getConstructor(Class.forName("ptviewer"), class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String).newInstance(this, var1)).init();
                        var4.start();
                        System.gc();
                    } catch (Exception var6) {
                    }
                }

                this.pheight = this.pdata.length;
                this.pwidth = this.pdata[0].length;
                if (this.pheight != this.pwidth >> 1) {
                    double var7 = (double) this.pheight / (double) this.pwidth * 180.0D;
                    if (this.pitch_max > var7) {
                        this.pitch_max = var7;
                    }

                    if (this.pitch_min < -var7) {
                        this.pitch_min = -var7;
                    }
                }

                if (this.hfov > this.yaw_max - this.yaw_min) {
                    this.hfov = this.yaw_max - this.yaw_min;
                }

                if (!this.PanoIsLoaded) {
                    this.math_setLookUp(this.pdata);
                }

                this.finishInit(this.PanoIsLoaded);
            }
        }
    }

    void finishInit(boolean var1) {
        if (!var1) {
            this.shs_setup();
        }

        this.ready = true;
        this.requestFocus();
        this.ResetCursor();
        this.repaint();
        this.paint(this.getGraphics());
        if (this.loadAllRoi && !this.PanoIsLoaded) {
            this.loadROI(0, this.numroi - 1);
        }

        if (!this.PanoIsLoaded) {
            this.hs_setup(this.pdata);
        }

        this.hsready = true;
        this.PanoIsLoaded = true;
        if (this.autopan != 0.0D) {
            this.lastframe = this.frames + 100000000L;
        }

        if (this.inits != null) {
            int var2;
            if ((var2 = this.inits.indexOf(42)) == -1) {
                this.JumpToLink(this.inits, (String) null);
            } else {
                this.JumpToLink(this.inits.substring(0, var2), this.inits.substring(var2 + 1));
            }
        }

        this.repaint();
        this.SetupSounds();
        if (this.preload != null && this.preloadthread == null) {
            this.preloadthread = new Thread(this);

            try {
                this.preloadthread.setPriority(1);
            } catch (SecurityException var4) {
            }

            this.preloadthread.start();
        }

    }

    public boolean mouseDown(Event var1, int var2, int var3) {
        if (var2 >= this.vx && var2 < this.vx + this.vwidth && var3 >= this.vy && var3 < this.vy + this.vheight) {
            if (this.lastframe > this.frames) {
                this.stopThread(this.ptviewerScript);
                this.ptviewerScript = null;
                this.stopAutoPan();
                this.oldx = var2;
                this.oldy = var3;
                return true;
            }

            if (this.showCoordinates) {
                this.showStatus(this.DisplayHSCoordinates(var2 - this.vx, var3 - this.vy));
                this.showCoordinates = false;
                return true;
            }
        }

        if (!this.panning && this.mouseInViewer) {
            this.oldx = var2;
            this.oldy = var3;
            if (this.curhs < 0) {
                this.panning = true;
                if (var1.shiftDown()) {
                    this.zoom = 0.970873786407767D;
                } else if (var1.controlDown()) {
                    this.zoom = 1.03D;
                } else {
                    this.zoom = 1.0D;
                }

                this.repaint();
                this.PVSetCursor(var2, var3);
            }
        }

        this.newx = var2;
        this.newy = var3;
        return true;
    }

    public boolean mouseDrag(Event var1, int var2, int var3) {
        this.newx = var2;
        this.newy = var3;
        if (this.mouseInViewer) {
            this.panning = true;
            if (var1.shiftDown()) {
                this.zoom = 0.970873786407767D;
            } else if (var1.controlDown()) {
                this.zoom = 1.03D;
            } else {
                this.zoom = 1.0D;
            }

            this.ResetCursor();
        }

        this.repaint();
        return true;
    }

    public boolean mouseUp(Event var1, int var2, int var3) {
        this.newx = var2;
        this.newy = var3;
        this.stopPan();
        this.zoom = 1.0D;
        if (this.hsready) {
            int var4;
            if (this.curshs >= 0) {
                for (var4 = 0; var4 < this.numshs; ++var4) {
                    if (this.shs_active[var4]) {
                        this.gotoSHS(var4);
                    }
                }
            } else if (this.curhs >= 0) {
                this.gotoHS(this.curhs);

                for (var4 = this.curhs + 1; var4 < this.numhs && this.curhs != -1; ++var4) {
                    if (this.hs_link[var4] == this.curhs) {
                        this.gotoHS(var4);
                    }
                }

                if (this.curhs < 0) {
                    return true;
                }
            }

            this.PVSetCursor(var2, var3);
            this.click_x = var2;
            this.click_y = var3;
        }

        return true;
    }

    public boolean mouseEnter(Event var1, int var2, int var3) {
        this.mouseInWindow = true;
        this.mouseInViewer = this.is_inside_viewer(var2, var3);
        this.PVSetCursor(var2, var3);
        return true;
    }

    public boolean mouseExit(Event var1, int var2, int var3) {
        this.mouseInWindow = this.mouseInViewer = false;
        this.stopPan();
        this.zoom = 1.0D;
        this.ResetCursor();
        return true;
    }

    public boolean keyDown(Event var1, int var2) {
        if (!this.ready) {
            return true;
        } else {
            switch (var2) {
                case 10:
                    if (this.hsready) {
                        int var3;
                        if (this.curshs >= 0) {
                            for (var3 = 0; var3 < this.numshs; ++var3) {
                                if (this.shs_active[var3]) {
                                    this.gotoSHS(var3);
                                }
                            }
                        } else if (!this.panning && this.curhs >= 0) {
                            this.gotoHS(this.curhs);

                            for (var3 = this.curhs + 1; var3 < this.numhs && this.curhs != -1; ++var3) {
                                if (this.hs_link[var3] == this.curhs) {
                                    this.gotoHS(var3);
                                }
                            }

                            if (this.curhs < 0) {
                                return true;
                            }
                        }
                    }
                    break;
                case 32:
                    this.toggleHS();
                    break;
                case 43:
                case 46:
                case 61:
                case 62:
                case 65:
                case 97:
                    this.ZoomIn();
                    break;
                case 44:
                case 45:
                case 60:
                case 90:
                case 95:
                case 122:
                    this.ZoomOut();
                    break;
                case 73:
                case 105:
                    this.showStatus(this.getAppletInfo());
                    break;
                case 80:
                case 112:
                    this.showStatus(this.m1());
                    break;
                case 85:
                case 117:
                    this.showStatus(this.getDocumentBase().toString());
                    break;
                case 104:
                    this.showCoordinates = true;
                    this.showStatus("Click Mouse to display X/Y Coordinates");
                    break;
                case 118:
                    this.showStatus("pan = " + (double) ((int) (this.yaw * 100.0D)) / 100.0D + "deg; tilt = " + (double) ((int) (this.pitch * 100.0D)) / 100.0D + "deg; fov = " + (double) ((int) (this.hfov * 100.0D)) / 100.0D + "deg");
                    break;
                case 1004:
                    this.panUp();
                    break;
                case 1005:
                    this.panDown();
                    break;
                case 1006:
                    this.panLeft();
                    break;
                case 1007:
                    this.panRight();
            }

            return true;
        }
    }

    public boolean mouseMove(Event var1, int var2, int var3) {
        this.mouseInViewer = this.is_inside_viewer(var2, var3);
        if (this.mouseInWindow) {
            this.newx = var2;
            this.newy = var3;
        }

        this.PVSetCursor(var2, var3);
        return true;
    }

    void PVSetCursor(int var1, int var2) {
        if (!this.mouseInWindow) {
            this.ResetCursor();
        } else {
            int var3;
            if (!this.ready) {
                var3 = -1;
            } else {
                var3 = this.OverStaticHotspot(var1, var2);
            }

            if (var3 != this.curshs) {
                this.curshs = var3;
                if (this.curshs >= 0) {
                    try {
                        ((Frame) this.getParent()).setCursor(12);
                    } catch (Exception var5) {
                    }

                    this.curhs = -1;
                    this.repaint();
                    return;
                }

                this.ResetCursor();
                this.repaint();
            }

            if (this.curshs < 0) {
                if (this.panning || this.lastframe > this.frames || !this.mouseInViewer) {
                    this.curhs = -1;
                    this.ResetCursor();
                    return;
                }

                if (!this.hsready) {
                    var3 = -1;
                } else {
                    var3 = this.OverHotspot(var1 - this.vx, var2 - this.vy);
                }

                if (var3 != this.curhs) {
                    this.curhs = var3;
                    if (this.curhs < 0) {
                        this.ResetCursor();
                        this.repaint();
                        this.showStatus("");
                        this.sendHS();
                        return;
                    }

                    try {
                        ((Frame) this.getParent()).setCursor(12);
                        if (this.hsready) {
                            this.showStatus(this.hs_name[this.curhs]);
                            this.hs_exec_popup(this.curhs);
                            this.repaint();
                            this.sendHS();
                        }

                        return;
                    } catch (Exception var6) {
                    }
                }

                if (this.curhs < 0) {
                    this.ResetCursor();
                }
            }

        }
    }

    void ResetCursor() {
        try {
            if (this.mouseInViewer) {
                if (!this.ready) {
                    ((Frame) this.getParent()).setCursor(3);
                    return;
                }

                if (((Frame) this.getParent()).getCursorType() != this.ptcursor) {
                    ((Frame) this.getParent()).setCursor(this.ptcursor);
                    return;
                }
            } else if (((Frame) this.getParent()).getCursorType() != 0) {
                ((Frame) this.getParent()).setCursor(0);
                return;
            }
        } catch (Exception var2) {
        }

    }

    void sendView() {
        if (this.GetView != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(this.GetView + "(" + this.yaw + "," + this.pitch + "," + this.hfov + ")");
        }

    }

    void sendHS() {
        if (this.MouseOverHS != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(this.MouseOverHS + "(" + this.curhs + ")");
        }

    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public synchronized void paint(Graphics var1) {
        if (this.inited) {
            if (this.fatal) {
                this.setBackground(Color.red);
                var1.clearRect(0, 0, this.size().width, this.size().height);
                return;
            }

            if (this.offImage == null) {
                this.awidth = this.size().width;
                this.aheight = this.size().height;
                if (!this.vset || this.offwidth == 0) {
                    this.offwidth = this.size().width;
                    this.offheight = this.size().height;
                }

                this.offImage = this.createImage(this.offwidth, this.offheight);
                this.offGraphics = this.offImage.getGraphics();
            }

            if (!this.ready || System.currentTimeMillis() < this.ptimer) {
                if (this.dwait != null) {
                    if (this.bgcolor != null && !this.WaitDisplayed) {
                        this.setBackground(this.bgcolor);
                        this.offGraphics.clearRect(0, 0, this.offwidth, this.offheight);
                    }

                    if (!this.WaitDisplayed) {
                        if (this.waittime != 0L) {
                            this.ptimer = System.currentTimeMillis() + this.waittime;
                        }

                        this.WaitDisplayed = true;
                    }

                    this.offGraphics.drawImage(this.dwait, this.offwidth - this.dwait.getWidth((ImageObserver) null) >> 1, this.offheight - this.dwait.getHeight((ImageObserver) null) >> 1, this);
                    this.pb_draw(this.offGraphics, this.offwidth, this.offheight);
                    var1.drawImage(this.offImage, 0, 0, this);
                    if (this.ready) {
                        try {
                            Thread.sleep(20L);
                        } catch (InterruptedException var11) {
                            return;
                        }

                        this.repaint();
                        return;
                    }
                } else {
                    if (this.bgcolor != null) {
                        this.setBackground(this.bgcolor);
                    }

                    var1.clearRect(0, 0, this.size().width, this.size().height);
                    if (this.percent != null && this.percent[0] > 0) {
                        var1.drawString("Loading Image..." + this.percent[0] + "% complete", 30, this.size().height >> 1);
                        return;
                    }

                    var1.drawString("Loading Image...", 30, this.size().height >> 1);
                }

                return;
            }

            double var2;
            int var4;
            if (this.vdata == null) {
                if (this.vwidth == 0) {
                    this.vwidth = this.size().width;
                }

                if (this.vheight == 0) {
                    this.vheight = this.size().height;
                }

                while (this.math_fovy(this.hfov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
                    this.hfov /= 1.03D;
                }

                var2 = this.math_fovy(this.hfov, this.vwidth, this.vheight) / 2.0D;
                if (this.pitch > this.pitch_max - var2 && this.pitch_max != 90.0D) {
                    this.pitch = 0.0D;
                }

                if (this.pitch < this.pitch_min + var2 && this.pitch_min != -90.0D) {
                    this.pitch = 0.0D;
                }

                this.vdata = new int[this.vwidth * this.vheight];
                this.hs_vdata = new byte[this.vwidth * this.vheight];
                if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    for (var4 = 0; var4 < this.hs_vdata.length; ++var4) {
                        this.hs_vdata[var4] = 0;
                    }
                } else {
                    for (int var5 = 0; var5 < this.hs_vdata.length; ++var5) {
                        this.hs_vdata[var5] = -1;
                    }
                }

                this.dirty = true;
                this.source = new MemoryImageSource(this.vwidth, this.vheight, this.vdata, 0, this.vwidth);
                this.source.setAnimated(true);
                if (this.view == null) {
                    this.view = this.createImage(this.source);
                }

                if (this.antialias && this.pdata != null) {
                    this.scaledPanos = new Vector();
                    this.scaledPanos.addElement(this.pdata);
                    int[][] var6 = this.pdata;
                    double var7 = this.hfov_max / ((double) this.vwidth * 360.0D * this.max_oversampling);

                    for (int var9 = 0; var6 != null && (double) var6[0].length * var7 > 1.0D; ++var9) {
                        var6 = this.im_halfsize(var6);
                        this.scaledPanos.addElement(var6);
                    }
                }
            }

            double var17;
            if (this.panning) {
                var2 = 5.0E-4D * this.hfov / 70.0D * 320.0D / (double) this.vwidth;
                var17 = ((double) ((this.newx - this.oldx) * (this.newx - this.oldx)) * (this.newx > this.oldx ? 1.0D : -1.0D) + this.MASS * this.oldspeedx) / (1.0D + this.MASS);
                this.oldspeedx = var17;
                double var21 = ((double) ((this.oldy - this.newy) * (this.oldy - this.newy)) * (this.oldy > this.newy ? 1.0D : -1.0D) + this.MASS * this.oldspeedy) / (1.0D + this.MASS);
                this.oldspeedy = var21;
                this.gotoView(this.yaw + var2 * var17, this.pitch + var2 * var21, this.hfov * this.zoom);
            }

            if (this.lastframe > this.frames) {
                this.gotoView(this.yaw + this.autopan, this.pitch + this.autotilt, this.hfov * this.zoom);
            }

            if (this.hsready && this.hs_drawWarpedImages(this.pdata, this.curhs, this.showhs)) {
                this.dirty = true;
            }

            if (this.dirty) {
                for (int var13 = 0; var13 < this.vdata.length; ++var13) {
                    this.vdata[var13] = 0;
                }

                if (this.app_properties.size() == 6 && this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    int[] var15 = this.get_cube_order((int) this.yaw, (int) this.pitch);

                    for (var4 = 0; var4 < 6; ++var4) {
                        Applet var20;
                        if ((var20 = (Applet) this.applets.get(this.app_properties.elementAt(var15[var4]))) != null && this.sender != null && this.sender.get(var20) != null) {
                            String var23 = var20.getAppletInfo();
                            if (this.dirty && var23 != null && var23.equals("topFrame")) {
                                var20.paint((Graphics) null);
                            }
                        }
                    }
                } else {
                    for (int var3 = 0; var3 < this.app_properties.size(); ++var3) {
                        Applet var18;
                        if ((var18 = (Applet) this.applets.get(this.app_properties.elementAt(var3))) != null && this.sender != null && this.sender.get(var18) != null) {
                            String var19 = var18.getAppletInfo();
                            if (this.dirty && var19 != null && var19.equals("topFrame")) {
                                var18.paint((Graphics) null);
                            }
                        }
                    }
                }

                if (this.dirty && this.show_pdata) {
                    int[][] var16 = this.pdata;
                    if (this.antialias && this.scaledPanos != null) {
                        var17 = this.hfov / ((double) this.vwidth * 360.0D * this.max_oversampling);
                        int var24 = 0;

                        for (int var25 = this.pdata[0].length; (double) var25 * var17 > 1.0D; var25 >>= 1) {
                            ++var24;
                        }

                        if (this.scaledPanos.elementAt(var24) != null) {
                            var16 = (int[][]) this.scaledPanos.elementAt(var24);
                            this.math_updateLookUp(var16[0].length);
                        }
                    }

                    switch (this.quality) {
                        case 0:
                            this.math_extractview(var16, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                            this.dirty = false;
                            break;
                        case 1:
                            if (!this.panning && this.lastframe <= this.frames) {
                                this.math_extractview(var16, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                                System.gc();
                                this.dirty = false;
                            } else {
                                this.math_extractview(var16, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                            }
                            break;
                        case 2:
                            if (this.panning) {
                                this.math_extractview(var16, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                            } else {
                                this.math_extractview(var16, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                                System.gc();
                                this.dirty = false;
                            }
                            break;
                        case 3:
                            this.math_extractview(var16, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                            this.dirty = false;
                    }
                }

                this.hs_setCoordinates(this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov);
                this.sendView();
                ++this.frames;
                this.source.newPixels();
            }

            if (this.panning || this.lastframe > this.frames) {
                this.PVSetCursor(this.newx, this.newy);
            }

            this.offGraphics.drawImage(this.view, this.vx, this.vy, this);
            if (this.hsready) {
                this.hs_draw(this.offGraphics, this.vx, this.vy, this.vwidth, this.vheight, this.curhs, this.showhs);
            }

            if (this.frame != null) {
                this.offGraphics.drawImage(this.frame, this.offwidth - this.frame.getWidth((ImageObserver) null), this.offheight - this.frame.getHeight((ImageObserver) null), this);
            }

            if (this.ready) {
                this.shs_draw(this.offGraphics);
            }

            Enumeration var14 = this.sender.elements();

            while (var14.hasMoreElements()) {
                try {
                    Applet var22;
                    if ((var22 = (Applet) var14.nextElement()).getAppletInfo() != "topFrame") {
                        var22.paint(this.offGraphics);
                    }
                } catch (Exception var12) {
                }
            }

            var1.drawImage(this.offImage, 0, 0, this);
        }

    }

    public void loadROI(int var1, int var2) {
        for (int var3 = var1; var3 <= var2; ++var3) {
            this.loadROI(var3);
        }

    }

    public void loadROI(int var1) {
        Image var2;
        if (var1 < this.numroi && !this.roi_loaded[var1] && (var2 = this.loadImage(this.roi_im[var1])) != null) {
            this.ptinsertImage(this.pdata, this.roi_xp[var1], this.roi_yp[var1], var2, (this.pheight + 99) / 100);
            if (this.hsready) {
                for (int var3 = 0; var3 < this.numhs; ++var3) {
                    if ((this.hs_imode[var3] & 4) > 0) {
                        int var4 = (int) this.hs_up[var3];
                        int var5 = (int) this.hs_vp[var3];
                        int var6 = (int) this.hs_xp[var3] - (var4 >> 1);
                        int var7 = (int) this.hs_yp[var3] - (var5 >> 1);
                        this.im_extractRect(this.pdata, var6, var7, (int[]) this.hs_him[var3], var4, 0, var5, var4, var5);
                    }
                }
            }

            this.roi_loaded[var1] = true;
        }

    }

    String DisplayHSCoordinates(int var1, int var2) {
        double[] var3;
        (var3 = this.math_view2pano(var1, var2, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov))[0] = Math.rint(var3[0] * 100000.0D / (double) this.pwidth) / 1000.0D;
        var3[1] = Math.rint(var3[1] * 100000.0D / (double) this.pheight) / 1000.0D;
        return "X = " + var3[0] + "; Y = " + var3[1];
    }

    int OverHotspot(int var1, int var2) {
        if (this.hsready && var1 >= 0 && var1 < this.vwidth && var2 >= 0 && var2 < this.vheight) {
            int var3 = this.hs_vdata[var2 * this.vwidth + var1] & 255;
            if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                return var3 == 0 ? -1 : var3 - 1;
            } else if (var3 != 255 && var3 < this.numhs) {
                return var3;
            } else if (this.hs_image != null) {
                return -1;
            } else {
                for (var3 = 0; var3 < this.numhs; ++var3) {
                    if (this.hs_visible[var3] && this.hs_mask[var3] == null && this.hs_link[var3] == -1 && this.hs_up[var3] == -200.0D && this.hs_vp[var3] == -200.0D && var1 < this.hs_xv[var3] + 12 && var1 > this.hs_xv[var3] - 12 && var2 < this.hs_yv[var3] + 12 && var2 > this.hs_yv[var3] - 12) {
                        return var3;
                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }

    public void waitWhilePanning() {
        while (this.lastframe > this.frames) {
            try {
                Thread.sleep(200L);
            } catch (Exception var2) {
                return;
            }
        }

    }

    public void ZoomIn() {
        this.gotoView(this.yaw, this.pitch, this.hfov / 1.03D);
    }

    public void ZoomOut() {
        this.gotoView(this.yaw, this.pitch, this.hfov * 1.03D);
    }

    public void panUp() {
        this.gotoView(this.yaw, this.pitch + this.hfov / this.pan_steps, this.hfov);
    }

    public void panDown() {
        this.gotoView(this.yaw, this.pitch - this.hfov / this.pan_steps, this.hfov);
    }

    public void panLeft() {
        this.gotoView(this.yaw - this.hfov / this.pan_steps, this.pitch, this.hfov);
    }

    public void panRight() {
        this.gotoView(this.yaw + this.hfov / this.pan_steps, this.pitch, this.hfov);
    }

    public void showHS() {
        this.showhs = true;
        this.repaint();
    }

    public void hideHS() {
        this.showhs = false;
        this.repaint();
    }

    public void toggleHS() {
        this.showhs = !this.showhs;
        this.repaint();
    }

    public boolean isVisibleHS() {
        return this.showhs;
    }

    public double pan() {
        return this.yaw;
    }

    public double tilt() {
        return this.pitch;
    }

    public double fov() {
        return this.hfov;
    }

    public void setQuality(int var1) {
        if (var1 >= 0 && var1 <= 3) {
            this.quality = var1;
            this.dirty = true;
            this.repaint();
        }

    }

    public void moveFromTo(double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
        double var14 = 0.0D;
        double var16 = (var7 - var5) / (double) var13;
        double var18 = Math.pow(var11 / var9, 1.0D / (double) var13);
        if (Math.abs(var3 - var1) >= 180.0D && this.yaw_max == 180.0D && this.yaw_min == -180.0D) {
            if (var3 > var1) {
                var14 = (var3 - var1 - 360.0D) / (double) var13;
            } else if (var3 < var1) {
                var14 = (var3 - var1 + 360.0D) / (double) var13;
            }
        } else {
            var14 = (var3 - var1) / (double) var13;
        }

        this.gotoView(var1, var5, var9);
        this.lastframe = this.frames + (long) var13;
        this.startAutoPan(var14, var16, var18);
    }

    public void moveTo(double var1, double var3, double var5, int var7) {
        this.moveFromTo(this.yaw, var1, this.pitch, var3, this.hfov, var5, var7);
    }

    public void startAutoPan(double var1, double var3, double var5) {
        this.autopan = var1;
        this.autotilt = var3;
        this.zoom = var5;
        if (this.lastframe <= this.frames) {
            this.lastframe = this.frames + 100000000L;
        }

        this.repaint();
    }

    public void stopAutoPan() {
        this.lastframe = 0L;
        this.autopan = 0.0D;
        this.autotilt = 0.0D;
        this.zoom = 1.0D;
    }

    void stopPan() {
        this.panning = false;
        this.oldspeedx = 0.0D;
        this.oldspeedy = 0.0D;
    }

    public boolean getAutoPan() {
        return this.lastframe > this.frames;
    }

    public void gotoView(double var1, double var3, double var5) {
        if (var1 != this.yaw || var3 != this.pitch || var5 != this.hfov) {
            while (var1 > 180.0D) {
                var1 -= 360.0D;
            }

            while (var1 < -180.0D) {
                var1 += 360.0D;
            }

            double var7 = this.math_fovy(var5, this.vwidth, this.vheight) / 2.0D;
            if (var3 > this.pitch_max - var7 && this.pitch_max != 90.0D) {
                var3 = this.pitch_max - var7;
            } else if (var3 > this.pitch_max) {
                var3 = this.pitch_max;
            } else if (var3 < this.pitch_min + var7 && this.pitch_min != -90.0D) {
                var3 = this.pitch_min + var7;
            } else if (var3 < this.pitch_min) {
                var3 = this.pitch_min;
            }

            if (this.yaw_max != 180.0D || this.yaw_min != -180.0D) {
                double var10 = this.math_view2pano(0, this.pitch > 0.0D ? 0 : this.vheight - 1, this.vwidth, this.vheight, this.pwidth, this.pheight, var1, var3, var5)[0];
                double var12;
                if ((var12 = this.math_view2pano(this.vwidth - 1, this.pitch > 0.0D ? 0 : this.vheight - 1, this.vwidth, this.vheight, this.pwidth, this.pheight, var1, var3, var5)[0]) - var10 > (this.yaw_max - this.yaw_min) / 360.0D * (double) this.pwidth) {
                    return;
                }

                if (var10 < (this.yaw_min + 180.0D) / 360.0D * (double) this.pwidth) {
                    if (this.lastframe > this.frames) {
                        this.autopan *= -1.0D;
                    }

                    var1 += this.yaw_min - (var10 / (double) this.pwidth * 360.0D - 180.0D);
                }

                if (var12 > (this.yaw_max + 180.0D) / 360.0D * (double) this.pwidth) {
                    if (this.lastframe > this.frames) {
                        this.autopan *= -1.0D;
                    }

                    var1 -= var12 / (double) this.pwidth * 360.0D - 180.0D - this.yaw_max;
                }
            }

            if (2.0D * var7 <= this.pitch_max - this.pitch_min && var5 <= this.hfov_max && var5 >= this.hfov_min && var5 <= this.yaw_max - this.yaw_min && var3 <= this.pitch_max && var3 >= this.pitch_min && var1 <= this.yaw_max && var1 >= this.yaw_min && (var1 != this.yaw || var3 != this.pitch || var5 != this.hfov)) {
                this.yaw = var1;
                this.pitch = var3;
                this.hfov = var5;
                this.dirty = true;
                this.repaint();
            } else {
                this.stopAutoPan();
            }
        }
    }

    public void gotoHS(int var1) {
        if (var1 >= 0 && var1 < this.numhs) {
            this.JumpToLink(this.hs_url[var1], this.hs_target[var1]);
        }
    }

    void gotoSHS(int var1) {
        if (var1 >= 0 && var1 < this.numshs) {
            this.JumpToLink(this.shs_url[var1], this.shs_target[var1]);
        }
    }

    void JumpToLink(String var1, String var2) {
        if (var1 != null) {
            if (var1.startsWith("ptviewer:")) {
                this.executePTViewerCommand(var1.substring(var1.indexOf(58) + 1));
                return;
            }

            if (var1.startsWith("javascript:")) {
                this.executeJavascriptCommand(var1.substring(var1.indexOf(58) + 1));
                return;
            }

            URL var3;
            try {
                var3 = new URL(this.getDocumentBase(), var1);
            } catch (MalformedURLException var5) {
                System.err.println("URL " + var1 + " ill-formed");
                return;
            }

            if (var2 == null) {
                this.getAppletContext().showDocument(var3);
                return;
            }

            this.getAppletContext().showDocument(var3, var2);
        }

    }

    public synchronized void newPanoFromList(int var1, double var2, double var4, double var6) {
        this.loadPanoFromList(var1);
        this.yaw = var2;
        this.pitch = var4;
        this.hfov = var6;
        this.repaint();
        this.start();
    }

    public synchronized void newPanoFromList(int var1) {
        this.loadPanoFromList(var1);
        this.repaint();
        this.start();
    }

    void loadPanoFromList(int var1) {
        String var2;
        if ((var2 = this.myGetParameter((String) null, "pano" + var1)) != null) {
            this.stop();
            this.PV_reset();
            this.initialize();
            this.CurrentPano = var1;
            if (this.PTViewer_Properties != null) {
                this.ReadParameters(this.PTViewer_Properties);
            }

            this.ReadParameters(var2);
        }

    }

    public void newPano(String var1) {
        this.stop();
        this.PV_reset();
        this.initialize();
        if (this.PTViewer_Properties != null) {
            this.ReadParameters(this.PTViewer_Properties);
        }

        this.ReadParameters(var1);
        this.repaint();
        this.start();
    }

    public void SetURL(String var1) {
        this.newPano("{file=" + var1 + "}");
    }

    void ReadParameters(String var1) {
        String var2;
        if ((var2 = this.myGetParameter(var1, "bgcolor")) != null) {
            this.bgcolor = new Color(Integer.parseInt(var2, 16));
        }

        if ((var2 = this.myGetParameter(var1, "barcolor")) != null) {
            this.pb_color = new Color(Integer.parseInt(var2, 16));
        }

        if ((var2 = this.myGetParameter(var1, "bar_x")) != null) {
            this.pb_x = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "bar_y")) != null) {
            this.pb_y = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "bar_width")) != null) {
            this.pb_width = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "bar_height")) != null) {
            this.pb_height = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "maxarray")) != null) {
            this.im_maxarray = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "view_width")) != null) {
            this.vwidth = Integer.parseInt(var2);
            this.vset = true;
        }

        if ((var2 = this.myGetParameter(var1, "view_height")) != null) {
            this.vheight = Integer.parseInt(var2);
            this.vset = true;
        }

        if ((var2 = this.myGetParameter(var1, "view_x")) != null) {
            this.vx = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "view_y")) != null) {
            this.vy = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "preload")) != null) {
            this.preload = var2;
        }

        if ((var2 = this.myGetParameter(var1, "cache")) != null && var2.equalsIgnoreCase("false")) {
            this.file_cachefiles = false;
        }

        if ((var2 = this.myGetParameter(var1, "cursor")) != null) {
            if (var2.equalsIgnoreCase("CROSSHAIR")) {
                this.ptcursor = 1;
            } else if (var2.equalsIgnoreCase("MOVE")) {
                this.ptcursor = 13;
            }
        }

        if ((var2 = this.myGetParameter(var1, "grid_bgcolor")) != null) {
            this.grid_bgcolor = Integer.parseInt(var2, 16);
        }

        if ((var2 = this.myGetParameter(var1, "grid_fgcolor")) != null) {
            this.grid_fgcolor = Integer.parseInt(var2, 16);
        }

        if ((var2 = this.myGetParameter(var1, "mass")) != null) {
            this.MASS = Double.valueOf(var2);
        }

        if (this.myGetParameter(var1, "antialias") != null) {
            this.antialias = true;
        }

        if ((var2 = this.myGetParameter(var1, "quality")) != null) {
            this.quality = Integer.parseInt(var2);
            if (this.quality < 0) {
                this.quality = 0;
            }

            if (this.quality > 3) {
                this.quality = 3;
            }
        }

        if ((var2 = this.myGetParameter(var1, "inits")) != null) {
            this.inits = var2;
        }

        double var5;
        if ((var2 = this.myGetParameter(var1, "tiltmin")) != null && (var5 = Double.valueOf(var2)) > -90.0D && var5 < 0.0D) {
            this.pitch_min = var5;
        }

        if ((var2 = this.myGetParameter(var1, "tiltmax")) != null && (var5 = Double.valueOf(var2)) < 90.0D && var5 > 0.0D) {
            this.pitch_max = var5;
        }

        if ((var2 = this.myGetParameter(var1, "tilt")) != null && (var5 = Double.valueOf(var2)) >= this.pitch_min && var5 <= this.pitch_max) {
            this.pitch = var5;
        }

        if ((var2 = this.myGetParameter(var1, "panmax")) != null) {
            this.yaw_max = Double.valueOf(var2);
        }

        if ((var2 = this.myGetParameter(var1, "panmin")) != null) {
            this.yaw_min = Double.valueOf(var2);
        }

        if ((var2 = this.myGetParameter(var1, "pan")) != null && (var5 = Double.valueOf(var2)) >= this.yaw_min && var5 <= this.yaw_max) {
            this.yaw = var5;
        }

        if ((var2 = this.myGetParameter(var1, "fovmax")) != null && (var5 = Double.valueOf(var2)) <= 165.0D) {
            this.hfov_max = var5 > this.yaw_max - this.yaw_min ? this.yaw_max - this.yaw_min : var5;
        }

        if ((var2 = this.myGetParameter(var1, "fovmin")) != null) {
            this.hfov_min = Double.valueOf(var2);
        }

        if ((var2 = this.myGetParameter(var1, "fov")) != null && (var5 = Double.valueOf(var2)) <= this.hfov_max && var5 >= this.hfov_min) {
            this.hfov = var5;
        }

        if ((var2 = this.myGetParameter(var1, "wait")) != null) {
            this.dwait = null;
            this.dwait = this.loadImage(var2);
            this.update(this.getGraphics());
        }

        if ((var2 = this.myGetParameter(var1, "auto")) != null) {
            this.autopan = Double.valueOf(var2);
        }

        if ((var2 = this.myGetParameter(var1, "mousehs")) != null) {
            this.MouseOverHS = var2;
        }

        if ((var2 = this.myGetParameter(var1, "getview")) != null) {
            this.GetView = var2;
        }

        if ((var2 = this.myGetParameter(var1, "frame")) != null) {
            this.frame = null;
            this.frame = this.loadImage(var2);
        }

        if ((var2 = this.myGetParameter(var1, "waittime")) != null) {
            this.waittime = (long) Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "hsimage")) != null) {
            this.hs_image = var2;
        }

        if ((var2 = this.myGetParameter(var1, "pwidth")) != null) {
            this.pwidth = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "pheight")) != null) {
            this.pheight = Integer.parseInt(var2);
        }

        if ((var2 = this.myGetParameter(var1, "loadAllRoi")) != null && var2.equalsIgnoreCase("false")) {
            this.loadAllRoi = false;
        }

        if ((var2 = this.myGetParameter(var1, "file")) != null) {
            this.filename = var2;
        }

        if ((var2 = this.myGetParameter(var1, "order")) != null) {
            this.order = var2;
        }

        if ((var2 = this.myGetParameter(var1, "oversampling")) != null) {
            this.max_oversampling = Double.valueOf(var2);
        }

        int var3;
        for (var3 = 0; var3 <= this.hotspots.size(); ++var3) {
            if ((var2 = this.myGetParameter(var1, "hotspot" + var3)) != null) {
                if (var3 < this.hotspots.size()) {
                    this.hotspots.setSize(var3);
                }

                this.hotspots.addElement(var2);
            }
        }

        this.numroi = 0;

        int var4;
        for (var4 = 0; this.myGetParameter(var1, "roi" + var4) != null; ++var4) {
        }

        if (var4 > 0) {
            this.roi_allocate(var4);

            for (var3 = 0; var3 < this.numroi; ++var3) {
                if ((var2 = this.myGetParameter(var1, "roi" + var3)) != null) {
                    this.ParseROILine(var2, var3);
                }
            }
        }

        for (var3 = 0; var3 <= this.shotspots.size(); ++var3) {
            if ((var2 = this.myGetParameter(var1, "shotspot" + var3)) != null) {
                if (var3 < this.shotspots.size()) {
                    this.shotspots.setSize(var3);
                }

                this.shotspots.addElement(var2);
            }
        }

        for (var3 = 0; var3 <= this.sounds.size(); ++var3) {
            if ((var2 = this.myGetParameter(var1, "sound" + var3)) != null) {
                if (var3 < this.sounds.size()) {
                    this.sounds.setSize(var3);
                }

                this.sounds.addElement(var2);
            }
        }

        for (var3 = 0; var3 <= this.app_properties.size(); ++var3) {
            if ((var2 = this.myGetParameter(var1, "applet" + var3)) != null) {
                if (var3 < this.app_properties.size()) {
                    this.stopApplets(var3);
                    this.app_properties.setSize(var3);
                }

                this.app_properties.addElement(var2);
            }
        }

    }

    void executeJavascriptCommand(String var1) {
        if (var1 != null) {
            try {
                Class var2;
                Object var3 = (var2 = Class.forName("netscape.javascript.JSObject")).getMethod("getWindow", class$java$applet$Applet == null ? (class$java$applet$Applet = class$("java.applet.Applet")) : class$java$applet$Applet).invoke(var2, this);
                var2.getMethod("eval", class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String).invoke(var3, var1);
                return;
            } catch (Exception var5) {
            }
        }

    }

    void executePTViewerCommand(String var1) {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = new Thread(this);
        this.PTScript = var1;
        this.ptviewerScript.start();
    }

    void PTViewerScript(String var1) {
        int var2;
        if ((var2 = this.getNumArgs(var1, ';')) > 0) {
            for (int var3 = 0; var3 < var2; ++var3) {
                String var4;
                if ((var4 = this.stripWhiteSpace(this.getArg(var3, var1, ';'))).equals("loop()")) {
                    var3 = -1;
                } else {
                    this.PTViewerCommand(var4);
                }
            }
        }

    }

    void PTViewerCommand(String var1) {
        String var2 = var1.substring(var1.indexOf(40) + 1, var1.indexOf(41));
        if (var1.startsWith("ZoomIn")) {
            this.ZoomIn();
        } else if (var1.startsWith("ZoomOut")) {
            this.ZoomOut();
        } else if (var1.startsWith("panUp")) {
            this.panUp();
        } else if (var1.startsWith("panDown")) {
            this.panDown();
        } else if (var1.startsWith("panLeft")) {
            this.panLeft();
        } else if (var1.startsWith("panRight")) {
            this.panRight();
        } else if (var1.startsWith("showHS")) {
            this.showHS();
        } else if (var1.startsWith("hideHS")) {
            this.hideHS();
        } else if (var1.startsWith("toggleHS")) {
            this.toggleHS();
        } else {
            if (var1.startsWith("gotoView")) {
                if (this.getNumArgs(var2) == 3) {
                    this.gotoView(Double.valueOf(this.getArg(0, var2)), Double.valueOf(this.getArg(1, var2)), Double.valueOf(this.getArg(2, var2)));
                    return;
                }
            } else if (var1.startsWith("startAutoPan")) {
                if (this.getNumArgs(var2) == 3) {
                    this.startAutoPan(Double.valueOf(this.getArg(0, var2)), Double.valueOf(this.getArg(1, var2)), Double.valueOf(this.getArg(2, var2)));
                    return;
                }
            } else {
                if (var1.startsWith("stopAutoPan")) {
                    this.stopAutoPan();
                    return;
                }

                if (var1.startsWith("newPanoFromList")) {
                    if (this.getNumArgs(var2) == 1) {
                        this.newPanoFromList(Integer.parseInt(var2));
                        return;
                    }

                    if (this.getNumArgs(var2) == 4) {
                        this.newPanoFromList(Integer.parseInt(this.getArg(0, var2)), Double.valueOf(this.getArg(1, var2)), Double.valueOf(this.getArg(2, var2)), Double.valueOf(this.getArg(3, var2)));
                        return;
                    }
                } else {
                    if (var1.startsWith("newPano")) {
                        this.newPano(var2);
                        return;
                    }

                    if (var1.startsWith("SetURL")) {
                        this.SetURL(var2);
                        return;
                    }

                    if (var1.startsWith("PlaySound")) {
                        this.PlaySound(Integer.parseInt(var2));
                        return;
                    }

                    if (var1.startsWith("moveFromTo")) {
                        if (this.getNumArgs(var2) == 7) {
                            this.moveFromTo(Double.valueOf(this.getArg(0, var2)), Double.valueOf(this.getArg(1, var2)), Double.valueOf(this.getArg(2, var2)), Double.valueOf(this.getArg(3, var2)), Double.valueOf(this.getArg(4, var2)), Double.valueOf(this.getArg(5, var2)), Integer.valueOf(this.getArg(6, var2)));
                            return;
                        }
                    } else if (var1.startsWith("moveTo")) {
                        if (this.getNumArgs(var2) == 4) {
                            this.moveTo(Double.valueOf(this.getArg(0, var2)), Double.valueOf(this.getArg(1, var2)), Double.valueOf(this.getArg(2, var2)), Integer.valueOf(this.getArg(3, var2)));
                            return;
                        }
                    } else {
                        if (var1.startsWith("DrawSHSImage")) {
                            this.DrawSHSImage(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("HideSHSImage")) {
                            this.HideSHSImage(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("DrawHSImage")) {
                            this.DrawHSImage(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("HideHSImage")) {
                            this.HideHSImage(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("ToggleHSImage")) {
                            this.ToggleHSImage(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("ToggleSHSImage")) {
                            this.ToggleSHSImage(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("waitWhilePanning")) {
                            this.waitWhilePanning();
                            return;
                        }

                        if (var1.startsWith("startApplet")) {
                            this.startApplet(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("stopApplet")) {
                            this.stopApplet(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("loadROI")) {
                            if (this.getNumArgs(var2) == 2) {
                                this.loadROI(Integer.valueOf(this.getArg(0, var2)), Integer.valueOf(this.getArg(1, var2)));
                                return;
                            }

                            this.loadROI(Integer.parseInt(var2));
                            return;
                        }

                        if (var1.startsWith("setQuality")) {
                            this.setQuality(Integer.parseInt(var2));
                        }
                    }
                }
            }

        }
    }

    public synchronized void DrawSHSImage(int var1) {
        if (var1 >= 0 && var1 < this.numshs && this.shs_imode[var1] != 2) {
            this.shs_imode[var1] = 2;
            this.repaint();
        }

    }

    public synchronized void HideSHSImage(int var1) {
        if (var1 >= 0 && var1 < this.numshs && this.shs_imode[var1] != 0) {
            this.shs_imode[var1] = 0;
            this.repaint();
        }

    }

    public synchronized void ToggleSHSImage(int var1) {
        if (var1 >= 0 && var1 < this.numshs) {
            if (this.shs_imode[var1] != 0) {
                this.HideSHSImage(var1);
                return;
            }

            if (this.shs_imode[var1] != 2) {
                this.DrawSHSImage(var1);
            }
        }

    }

    public synchronized void DrawHSImage(int var1) {
        if (var1 >= 0 && var1 < this.numhs && (this.hs_imode[var1] & 2) == 0) {
            int[] var10000 = this.hs_imode;
            var10000[var1] |= 2;
            this.repaint();
        }

    }

    public synchronized void HideHSImage(int var1) {
        if (var1 >= 0 && var1 < this.numhs && (this.hs_imode[var1] & 2) != 0) {
            int[] var10000 = this.hs_imode;
            var10000[var1] &= -3;
            this.repaint();
        }

    }

    public synchronized void ToggleHSImage(int var1) {
        if (var1 >= 0 && var1 < this.numhs) {
            if ((this.hs_imode[var1] & 2) != 0) {
                this.HideHSImage(var1);
                return;
            }

            if ((this.hs_imode[var1] & 2) == 0) {
                this.DrawHSImage(var1);
            }
        }

    }

    public double get_x() {
        double var2 = -1.0D;
        if (this.click_x >= 0 && this.click_y >= 0) {
            var2 = (double) this.math_int_view2pano(this.click_x - this.vx, this.click_y - this.vy, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov)[0] * 100.0D / (double) this.pwidth;
        }

        return var2;
    }

    public double get_y() {
        double var2 = -1.0D;
        if (this.click_x >= 0 && this.click_y >= 0) {
            var2 = (double) this.math_int_view2pano(this.click_x - this.vx, this.click_y - this.vy, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov)[1] * 100.0D / (double) this.pheight;
        }

        this.click_x = -1;
        this.click_y = -1;
        return var2;
    }

    public int getPanoNumber() {
        return this.CurrentPano;
    }

    public void startCommunicating(Applet var1) {
        synchronized (this.sender) {
            if (var1 != null) {
                this.sender.put(var1, var1);
            } else {
                this.sender.clear();
            }
        }

        this.dirty = true;
        this.repaint();
    }

    public void stopCommunicating(Applet var1) {
        if (var1 != null) {
            synchronized (this.sender) {
                this.sender.remove(var1);
            }

            this.dirty = true;
            this.repaint();
        }

    }

    private String m1() {
        String var1;
        int var2;
        if ((var2 = (var1 = this.getDocumentBase().getFile()).indexOf(58)) != -1 && var2 + 1 < var1.length()) {
            return var1.substring(var2 + 1);
        } else {
            return (var2 = var1.indexOf(124)) != -1 && var2 + 1 < var1.length() ? var1.substring(var2 + 1) : var1;
        }
    }

    void stopThread(Thread var1) {
        if (var1 != null && var1.isAlive()) {
            try {
                var1.checkAccess();
                var1.stop();
                return;
            } catch (SecurityException var3) {
                var1.destroy();
            }
        }

    }

    void ptinsertImage(int[][] var1, int var2, int var3, Image var4, int var5) {
        if (var4 != null) {
            int var6 = var4.getWidth((ImageObserver) null);
            int var7 = var4.getHeight((ImageObserver) null);
            if (var5 > var7) {
                var5 = var7;
            }

            int var8 = (var7 + var5 - 1) / var5;
            int[] var9 = new int[var6 * var8];

            for (int var10 = 0; var10 < var5; ++var10) {
                int var11 = var8 + var10 * var8 > var7 ? var7 - var10 * var8 : var8;
                PixelGrabber var12 = new PixelGrabber(var4, 0, var10 * var8, var6, var11, var9, 0, var6);

                try {
                    var12.grabPixels();
                } catch (InterruptedException var14) {
                    return;
                }

                this.im_insertRect(var1, var2, var3 + var10 * var8, var9, var6, 0, 0, var6, var11);
                this.dirty = true;
                this.repaint();
            }
        }

    }

    boolean is_inside_viewer(int var1, int var2) {
        return var1 >= this.vx && var2 >= this.vy && var1 < this.vx + this.vwidth && var2 < this.vy + this.vheight;
    }

    int[] get_cube_order(int var1, int var2) {
        int[] var3;
        (var3 = new int[6])[0] = 0;
        var3[1] = 1;
        var3[2] = 2;
        var3[3] = 3;
        var3[4] = 4;
        var3[5] = 5;
        if (var2 > 45) {
            var3[0] = 4;
            switch (var1 / 45) {
                case -3:
                    var3[1] = 1;
                    var3[2] = 0;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = 5;
                    break;
                case -2:
                    var3[1] = 1;
                    var3[2] = 0;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = 5;
                    break;
                case -1:
                    var3[1] = 2;
                    var3[2] = 1;
                    var3[3] = 3;
                    var3[4] = 0;
                    var3[5] = 5;
                    break;
                case 0:
                    var3[1] = 2;
                    var3[2] = 3;
                    var3[3] = 1;
                    var3[4] = 0;
                    var3[5] = 5;
                    break;
                case 1:
                    var3[1] = 3;
                    var3[2] = 2;
                    var3[3] = 1;
                    var3[4] = 0;
                    var3[5] = 5;
                    break;
                case 2:
                    var3[1] = 3;
                    var3[2] = 0;
                    var3[3] = 1;
                    var3[4] = 2;
                    var3[5] = 5;
                    break;
                case 3:
                    var3[1] = 0;
                    var3[2] = 3;
                    var3[3] = 1;
                    var3[4] = 2;
                    var3[5] = 5;
                    break;
                default:
                    var3[1] = 0;
                    var3[2] = 1;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = 5;
            }
        } else if (var2 < -45) {
            var3[0] = 5;
            switch (var1 / 45) {
                case -3:
                    var3[1] = 1;
                    var3[2] = 0;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = 4;
                    break;
                case -2:
                    var3[1] = 1;
                    var3[2] = 0;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = 4;
                    break;
                case -1:
                    var3[1] = 2;
                    var3[2] = 1;
                    var3[3] = 3;
                    var3[4] = 0;
                    var3[5] = 4;
                    break;
                case 0:
                    var3[1] = 2;
                    var3[2] = 3;
                    var3[3] = 1;
                    var3[4] = 0;
                    var3[5] = 4;
                    break;
                case 1:
                    var3[1] = 3;
                    var3[2] = 2;
                    var3[3] = 1;
                    var3[4] = 0;
                    var3[5] = 4;
                    break;
                case 2:
                    var3[1] = 3;
                    var3[2] = 0;
                    var3[3] = 1;
                    var3[4] = 2;
                    var3[5] = 4;
                    break;
                case 3:
                    var3[1] = 0;
                    var3[2] = 3;
                    var3[3] = 1;
                    var3[4] = 2;
                    var3[5] = 4;
                    break;
                default:
                    var3[1] = 0;
                    var3[2] = 1;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = 4;
            }
        } else {
            switch (var1 / 45) {
                case -3:
                    var3[0] = 1;
                    var3[1] = 0;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                case -2:
                    var3[0] = 1;
                    var3[1] = 0;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                case -1:
                    var3[0] = 2;
                    var3[1] = 1;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 3;
                    var3[4] = 0;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                case 0:
                    var3[0] = 2;
                    var3[1] = 3;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 1;
                    var3[4] = 0;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                case 1:
                    var3[0] = 3;
                    var3[1] = 2;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 1;
                    var3[4] = 0;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                case 2:
                    var3[0] = 3;
                    var3[1] = 0;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 1;
                    var3[4] = 2;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                case 3:
                    var3[0] = 0;
                    var3[1] = 3;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 1;
                    var3[4] = 2;
                    var3[5] = var2 > 0 ? 5 : 4;
                    break;
                default:
                    var3[0] = 0;
                    var3[1] = 1;
                    var3[2] = var2 > 0 ? 4 : 5;
                    var3[3] = 3;
                    var3[4] = 2;
                    var3[5] = var2 > 0 ? 5 : 4;
            }
        }

        return var3;
    }

    public Image loadImage(String var1) {
        Image var2;
        byte[] var3;
        if ((var3 = this.file_read(var1, (int[]) null)) != null && (var2 = this.bufferToImage(var3)) != null) {
            return var2;
        } else {
            try {
                URL var4 = new URL(this.getDocumentBase(), var1);
                var2 = this.getImage(var4);
                MediaTracker var5;
                (var5 = new MediaTracker(this)).addImage(var2, 0);
                var5.waitForAll();
                return var2 != null && var2.getWidth((ImageObserver) null) > 0 ? var2 : null;
            } catch (Exception var7) {
                return null;
            }
        }
    }

    Image loadImageProgress(String var1) {
        this.percent[0] = 0;
        byte[] var3;
        if ((var3 = this.file_read(var1, this.percent)) != null) {
            Image var2 = this.bufferToImage(var3);
            this.percent[0] = 100;
            this.repaint();
            if (var2 != null) {
                return var2;
            }
        }

        return this.loadImage(var1);
    }

    Image bufferToImage(byte[] var1) {
        if (var1 == null) {
            return null;
        } else {
            Image var2 = Toolkit.getDefaultToolkit().createImage(var1);
            MediaTracker var3;
            (var3 = new MediaTracker(this)).addImage(var2, 0);

            try {
                var3.waitForAll();
                return var2;
            } catch (InterruptedException var5) {
                return null;
            }
        }
    }

    int[][] im_allocate_pano(int[][] var1, int var2, int var3) {
        if (var1 != null && var1.length == var3 && var1[0].length == var2) {
            return var1;
        } else {
            try {
                return new int[var3][var2];
            } catch (Exception var5) {
                return null;
            }
        }
    }

    void im_drawGrid(int[][] var1, int var2, int var3) {
        int var8 = var2 | -16777216;
        int var9 = var3 | -16777216;
        if (var1 != null) {
            int var10 = var1.length;
            int var11 = var1[0].length;

            int var4;
            int var5;
            for (var5 = 0; var5 < var10; ++var5) {
                for (var4 = 0; var4 < var11; ++var4) {
                    var1[var5][var4] = var8;
                }
            }

            var5 = 0;

            int var6;
            int var7;
            for (var6 = 36 * var10 / var11; var6 >= 0; --var6) {
                var7 = var5 + 1;

                for (var4 = 0; var4 < var11; ++var4) {
                    var1[var5][var4] = var9;
                    var1[var7][var4] = var9;
                }

                if (var6 != 0) {
                    var5 += (var10 - 2 - var5) / var6;
                }
            }

            var4 = 0;

            for (var6 = 36; var6 >= 0; --var6) {
                if (var4 == 0) {
                    for (var5 = 0; var5 < var10; ++var5) {
                        var1[var5][var4] = var9;
                    }
                } else if (var4 >= var11 - 1) {
                    var4 = var11 - 1;
                    var6 = 0;

                    for (var5 = 0; var5 < var10; ++var5) {
                        var1[var5][var4] = var9;
                    }
                } else {
                    var7 = var4 + 1;

                    for (var5 = 0; var5 < var10; ++var5) {
                        var1[var5][var4] = var9;
                        var1[var5][var7] = var9;
                    }
                }

                if (var6 != 0) {
                    var4 += (var11 - 1 - var4) / var6;
                }
            }
        }

    }

    void SetPAlpha(int var1, int var2, int var3, int var4, int var5, int[][] var6) {
        int var9 = (var5 << 24) + 16777215;
        int var10 = var6.length;
        int var11 = var6[0].length;
        int var12;
        if ((var12 = Math.min(var2, var4)) < 0) {
            var12 = 0;
        }

        int var13;
        if ((var13 = Math.max(var2, var4)) >= var10) {
            var13 = var10 - 1;
        }

        if (var1 < 0) {
            var1 = 0;
        }

        if (var1 >= var11) {
            var1 = var11 - 1;
        }

        if (var3 < 0) {
            var3 = 0;
        }

        if (var3 >= var11) {
            var3 = var11 - 1;
        }

        int var7;
        int var8;
        if (var3 >= var1) {
            for (var8 = var12; var8 <= var13; ++var8) {
                for (var7 = var1; var7 <= var3; ++var7) {
                    var6[var8][var7] &= var9;
                }
            }

        } else {
            for (var8 = var12; var8 <= var13; ++var8) {
                for (var7 = 0; var7 <= var3; ++var7) {
                    var6[var8][var7] &= var9;
                }

                for (var7 = var1; var7 < var11; ++var7) {
                    var6[var8][var7] &= var9;
                }
            }

        }
    }

    void scaleImage(int[][] var1, int var2, int var3) {
        if (var1 != null) {
            int var4 = var1.length;
            int var5 = var1[0].length;
            int var18 = 256 * var2 / var5;
            int var19 = (var5 << 7) - 128;
            int var20 = (var4 << 7) - 128;
            int var21 = (var2 << 7) - 128;
            int var22 = (var3 << 7) - 128;
            int var23 = -var19 * var2 / var5 + var21;
            int var24 = var2 - 1;

            for (int var7 = var4 - 1; var7 >= 0; --var7) {
                int var9;
                int var13 = (var9 = ((var7 << 8) - var20) * var2 / var5 + var22) & 255;
                int var16;
                int var17;
                if ((var9 >>= 8) < 0) {
                    var17 = 0;
                    var16 = 0;
                } else if (var9 >= var3 - 1) {
                    var16 = var17 = var3 - 1;
                } else {
                    var16 = var9++;
                    var17 = var9;
                }

                for (int var6 = var5 - 1; var6 >= 0; --var6) {
                    int var8;
                    int var12 = (var8 = var6 * var18 + var23) & 255;
                    int var14;
                    int var15;
                    if ((var8 >>= 8) < 0) {
                        var15 = 0;
                        var14 = 0;
                    } else if (var8 >= var24) {
                        var15 = var24;
                        var14 = var24;
                    } else {
                        var14 = var8++;
                        var15 = var8;
                    }

                    var1[var7][var6] = bil(var1[var16][var14], var1[var16][var15], var1[var17][var14], var1[var17][var15], var12, var13);
                }
            }
        }

    }

    void ptImageTo2DArray(int[][] var1, Image var2) {
        if (var2 != null && var1 != null) {
            int var3;
            if ((var3 = var2.getHeight((ImageObserver) null)) * var2.getWidth((ImageObserver) null) > this.im_maxarray) {
                var3 = this.im_maxarray / var2.getWidth((ImageObserver) null);
            }

            int[] var4 = new int[var3 * var2.getWidth((ImageObserver) null)];

            for (int var5 = 0; var5 < var2.getHeight((ImageObserver) null); var5 += var3) {
                int var10 = var3 < var2.getHeight((ImageObserver) null) - var5 ? var3 : var2.getHeight((ImageObserver) null) - var5;
                PixelGrabber var9 = new PixelGrabber(var2, 0, var5, var2.getWidth((ImageObserver) null), var10, var4, 0, var2.getWidth((ImageObserver) null));

                try {
                    var9.grabPixels();
                } catch (InterruptedException var12) {
                    return;
                }

                for (int var8 = 0; var8 < var10; ++var8) {
                    int var6 = var8 * var2.getWidth((ImageObserver) null);

                    for (int var7 = 0; var7 < var2.getWidth((ImageObserver) null); ++var7) {
                        var1[var8 + var5][var7] = var4[var6 + var7] | -16777216;
                    }
                }
            }

            System.gc();
        }
    }

    void ptImageToAlpha(int[][] var1, Image var2) {
        if (var2 != null && var1 != null) {
            int var3;
            if ((var3 = var2.getHeight((ImageObserver) null)) * var2.getWidth((ImageObserver) null) > this.im_maxarray) {
                var3 = this.im_maxarray / var2.getWidth((ImageObserver) null);
            }

            int[] var4 = new int[var3 * var2.getWidth((ImageObserver) null)];

            for (int var6 = 0; var6 < var2.getHeight((ImageObserver) null); var6 += var3) {
                int var11 = var3 < var2.getHeight((ImageObserver) null) - var6 ? var3 : var2.getHeight((ImageObserver) null) - var6;
                PixelGrabber var10 = new PixelGrabber(var2, 0, var6, var2.getWidth((ImageObserver) null), var11, var4, 0, var2.getWidth((ImageObserver) null));

                try {
                    var10.grabPixels();
                } catch (InterruptedException var13) {
                    return;
                }

                for (int var9 = 0; var9 < var11; ++var9) {
                    int var7 = var9 * var2.getWidth((ImageObserver) null);

                    for (int var8 = 0; var8 < var2.getWidth((ImageObserver) null); ++var8) {
                        int var5 = ((var4[var7 + var8] & 255) << 24) + 16777215;
                        var1[var9 + var6][var8] &= var5;
                    }
                }
            }

            System.gc();
        }
    }

    void im_insertRect(int[][] var1, int var2, int var3, int[] var4, int var5, int var6, int var7, int var8, int var9) {
        try {
            int var11 = 0;

            for (int var13 = var3; var11 < var9; ++var13) {
                int var10 = 0;

                for (int var14 = (var7 + var11) * var5 + var6; var10 < var8; ++var14) {
                    int var15;
                    if (((var15 = var4[var14]) & -16777216) != 0) {
                        int var12 = var10 + var2;
                        var1[var13][var12] = var15 & (var1[var13][var12] | 16777215);
                    }

                    ++var10;
                }

                ++var11;
            }

        } catch (Exception var17) {
            System.out.println("Insert can't be fit into panorama");
        }
    }

    final void im_extractRect(int[][] var1, int var2, int var3, int[] var4, int var5, int var6, int var7, int var8, int var9) {
        try {
            int var11 = 0;

            for (int var12 = var3; var11 < var9; ++var12) {
                int var10 = 0;

                for (int var13 = (var7 + var11) * var5 + var6; var10 < var8; ++var13) {
                    var4[var13] = this.pdata[var12][var10 + var2] | -16777216;
                    ++var10;
                }

                ++var11;
            }

        } catch (Exception var15) {
            System.out.println("Invalid rectangle");
        }
    }

    final int[][] im_loadPano(String var1, int[][] var2, int var3, int var4) {
        int[][] var5;
        if (var1 != null && !var1.equals("_PT_Grid")) {
            Image var6;
            if ((var6 = this.loadImageProgress(var1)) == null) {
                return null;
            } else {
                if (var3 > var6.getWidth((ImageObserver) null)) {
                    if (var4 == 0) {
                        var4 = var3 >> 1;
                    }
                } else {
                    var3 = var6.getWidth((ImageObserver) null);
                    var4 = var6.getHeight((ImageObserver) null);
                }

                if ((var5 = this.im_allocate_pano(var2, var3, var4)) == null) {
                    return null;
                } else {
                    this.ptImageTo2DArray(var5, var6);
                    if (var3 != var6.getWidth((ImageObserver) null)) {
                        this.scaleImage(var5, var6.getWidth((ImageObserver) null), var6.getHeight((ImageObserver) null));
                    }

                    return var5;
                }
            }
        } else {
            if (var3 == 0) {
                var3 = 100;
            }

            var5 = this.im_allocate_pano(var2, var3, var4 == 0 ? var3 >> 1 : var4);
            this.im_drawGrid(var5, this.grid_bgcolor, this.grid_fgcolor);
            return var5;
        }
    }

    int[][] im_halfsize(int[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var2 >> 1;
        int var5 = var3 >> 1;
        int[][] var6;
        if ((var6 = new int[var4][var5]) == null) {
            return null;
        } else {
            int var7 = 0;
            int var8 = 0;

            for (int var9 = 1; var7 < var4; var9 += 2) {
                int[] var10 = var1[var8];
                int[] var11 = var1[var9];
                int[] var12 = var6[var7];
                int var13 = 0;
                int var14 = 0;

                for (int var15 = 1; var13 < var5; var15 += 2) {
                    var12[var13] = im_pixelaverage(var10[var14], var10[var15], var11[var14], var11[var15]);
                    ++var13;
                    var14 += 2;
                }

                ++var7;
                var8 += 2;
            }

            return var6;
        }
    }

    byte[][] im_halfsize(byte[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var2 >> 1;
        int var5 = var3 >> 1;
        byte[][] var6;
        if ((var6 = new byte[var4][var5]) == null) {
            return null;
        } else {
            int var7 = 0;

            for (int var8 = 0; var7 < var4; var8 += 2) {
                byte[] var9 = var1[var8];
                byte[] var10 = var6[var7];
                int var11 = 0;

                for (int var12 = 0; var11 < var5; var12 += 2) {
                    var10[var11] = var9[var12];
                    ++var11;
                }

                ++var7;
            }

            return var6;
        }
    }

    static final int im_pixelaverage(int var0, int var1, int var2, int var3) {
        int var4;
        if ((var4 = (var0 >> 16 & 255) + (var1 >> 16 & 255) + (var2 >> 16 & 255) + (var3 >> 16 & 255) >> 2) < 0) {
            var4 = 0;
        }

        if (var4 > 255) {
            var4 = 255;
        }

        int var5;
        if ((var5 = (var0 >> 8 & 255) + (var1 >> 8 & 255) + (var2 >> 8 & 255) + (var3 >> 8 & 255) >> 2) < 0) {
            var5 = 0;
        }

        if (var5 > 255) {
            var5 = 255;
        }

        int var6;
        if ((var6 = (var0 & 255) + (var1 & 255) + (var2 & 255) + (var3 & 255) >> 2) < 0) {
            var6 = 0;
        }

        if (var6 > 255) {
            var6 = 255;
        }

        return (var0 & -16777216) + (var4 << 16) + (var5 << 8) + var6;
    }

    String resolveQuotes(String var1) {
        if (var1 == null) {
            return null;
        } else {
            int var3;
            if ((var3 = var1.length()) < 6) {
                return var1;
            } else {
                StringBuffer var4 = new StringBuffer(0);

                int var2;
                for (var2 = 0; var2 < var3 - 5; ++var2) {
                    if (var1.substring(var2, var2 + 6).equalsIgnoreCase("&quot;")) {
                        var4.append('"');
                        var2 += 5;
                    } else {
                        var4.append(var1.charAt(var2));
                    }
                }

                var4.append(var1.substring(var2, var3));
                return var4.toString();
            }
        }
    }

    String stripWhiteSpace(String var1) {
        if (var1 == null) {
            return null;
        } else {
            int var2 = 0;

            int var3;
            int var4;
            for (var4 = (var3 = var1.length()) - 1; var2 < var3 && (var1.charAt(var2) == ' ' || var1.charAt(var2) == '\r' || var1.charAt(var2) == '\n' || var1.charAt(var2) == '\t'); ++var2) {
            }

            if (var2 == var3) {
                return null;
            } else {
                while (var4 >= 0 && (var1.charAt(var4) == ' ' || var1.charAt(var4) == '\r' || var1.charAt(var4) == '\n' || var1.charAt(var4) == '\t')) {
                    --var4;
                }

                return var4 >= 0 && var4 >= var2 ? var1.substring(var2, var4 + 1) : null;
            }
        }
    }

    Dimension string_textWindowSize(Graphics var1, String var2) {
        FontMetrics var3 = var1.getFontMetrics();
        int var4 = 0;
        int var6 = 1;

        int var5;
        int var7;
        int var8;
        for (var7 = 0; (var5 = var2.indexOf(124, var4)) != -1 && var5 < var2.length() - 1; var4 = var5 + 1) {
            if ((var8 = var3.stringWidth(var2.substring(var4, var5))) > var7) {
                var7 = var8;
            }

            ++var6;
        }

        if ((var8 = var3.stringWidth(var2.substring(var4))) > var7) {
            var7 = var8;
        }

        return new Dimension(var7 + 10, var6 * var3.getHeight() + (var3.getHeight() >> 1));
    }

    void string_drawTextWindow(Graphics var1, int var2, int var3, Dimension var4, Color var5, String var6, int var7) {
        var1.clearRect(var2, var3, var4.width, var4.height);
        if (var5 == null) {
            var1.setColor(Color.black);
        } else {
            var1.setColor(var5);
        }

        FontMetrics var8 = var1.getFontMetrics();
        int var9 = 0;

        int var10;
        int var11;
        for (var11 = 1; (var10 = var6.indexOf(124, var9)) != -1 && var10 < var6.length() - 1; var9 = var10 + 1) {
            var1.drawString(var6.substring(var9, var10), var2 + 5, var3 + var11 * var8.getHeight());
            ++var11;
        }

        var1.drawString(var6.substring(var9), var2 + 5, var3 + var11 * var8.getHeight());
        switch (var7) {
            case 1:
                var1.fillRect(var2, var3 + var4.height - 2, 2, 2);
                return;
            case 2:
                var1.fillRect(var2, var3, 2, 2);
                return;
            case 3:
                var1.fillRect(var2 + var4.width - 2, var3 + var4.height - 2, 2, 2);
                return;
            case 4:
                var1.fillRect(var2 + var4.width - 2, var3, 2, 2);
            default:
        }
    }

    public String myGetParameter(String var1, String var2) {
        String var3;
        if (var1 == null) {
            if ((var3 = this.resolveQuotes(this.getParameter(var2))) != null) {
                return var3;
            }
        } else if ((var3 = this.extractParameter(var1, var2)) != null) {
            return var3;
        }

        return this.extractParameter(this.PTViewer_Properties, var2);
    }

    String extractParameter(String var1, String var2) {
        int var4 = 0;
        if (var1 != null && var2 != null) {
            int var3;
            while ((var3 = var1.indexOf(123, var4)) >= 0 && (var4 = var1.indexOf(125, var3)) >= 0) {
                String var5;
                if ((var5 = this.stripWhiteSpace(var1.substring(var3 + 1, var4))).startsWith(var2 + "=")) {
                    return this.resolveQuotes(this.stripWhiteSpace(var5.substring(var5.indexOf(61) + 1)));
                }
            }

            return null;
        } else {
            return null;
        }
    }

    int getNextWord(int var1, String var2, StringBuffer var3) {
        int var4 = var1;
        int var5 = var2.length();
        if (var1 >= var5) {
            return var1;
        } else if (var2.charAt(var1) == '\'') {
            ++var1;
            if (var1 == var5) {
                var3.setLength(0);
                return var1;
            } else {
                for (var4 = var1; var1 < var5 && var2.charAt(var1) != '\''; ++var1) {
                }

                if (var1 < var5) {
                    var3.insert(0, var2.substring(var4, var1));
                    var3.setLength(var2.substring(var4, var1).length());
                } else {
                    var3.insert(0, var2.substring(var4));
                    var3.setLength(var2.substring(var4).length());
                }

                return var1;
            }
        } else if (var2.charAt(var1) == '$') {
            ++var1;
            if (var1 == var5) {
                var3.setLength(0);
                return var1;
            } else {
                char var6 = var2.charAt(var1);
                ++var1;
                if (var1 == var5) {
                    var3.setLength(0);
                    return var1;
                } else {
                    for (var4 = var1; var1 < var5 && var2.charAt(var1) != var6; ++var1) {
                    }

                    if (var1 < var5) {
                        var3.insert(0, var2.substring(var4, var1));
                        var3.setLength(var2.substring(var4, var1).length());
                    } else {
                        var3.insert(0, var2.substring(var4));
                        var3.setLength(var2.substring(var4).length());
                    }

                    return var1;
                }
            }
        } else {
            while (var1 < var5 && var2.charAt(var1) != ' ' && var2.charAt(var1) != '\r' && var2.charAt(var1) != '\n' && var2.charAt(var1) != '\t') {
                ++var1;
            }

            if (var1 < var5) {
                var3.insert(0, var2.substring(var4, var1));
                var3.setLength(var2.substring(var4, var1).length());
            } else {
                var3.insert(0, var2.substring(var4));
                var3.setLength(var2.substring(var4).length());
            }

            return var1;
        }
    }

    final String getArg(int var1, String var2, char var3) {
        int var5 = 0;
        if (var2 == null) {
            return null;
        } else {
            for (int var4 = 0; var4 < var1; ++var4) {
                if ((var5 = var2.indexOf(var3, var5)) == -1) {
                    return null;
                }

                ++var5;
            }

            int var6;
            return (var6 = var2.indexOf(var3, var5)) == -1 ? var2.substring(var5) : var2.substring(var5, var6);
        }
    }

    final String getArg(int var1, String var2) {
        return this.getArg(var1, var2, ',');
    }

    final int getNumArgs(String var1) {
        return this.getNumArgs(var1, ',');
    }

    final int getNumArgs(String var1, char var2) {
        int var4 = 0;
        if (var1 == null) {
            return 0;
        } else {
            int var3;
            for (var3 = 1; (var4 = var1.indexOf(var2, var4)) != -1; ++var3) {
                ++var4;
            }

            return var3;
        }
    }

    void file_init() {
        this.file_cachefiles = true;
        this.file_Cache = new Hashtable();
    }

    void file_dispose() {
        if (this.file_Cache != null) {
            this.file_Cache.clear();
            this.file_Cache = null;
        }

    }

    byte[] file_read(String var1, int[] var2) {
        byte[] var3;
        if ((var3 = (byte[]) this.file_Cache.get(var1)) != null) {
            if (var2 != null) {
                var2[0] = 80;
                this.repaint();
            }

            return var3;
        } else {
            int var4;
            InputStream var5;
            URLConnection var7;
            try {
                (var7 = (new URL(this.getDocumentBase(), var1)).openConnection()).setUseCaches(true);

                try {
                    var4 = var7.getContentLength();
                } catch (Exception var19) {
                    var4 = 0;
                }

                var5 = var7.getInputStream();
                var3 = this.file_read(var5, var4, var2);
                var5.close();
                if (var3 != null) {
                    this.m3(var3, var1);
                    if (this.file_cachefiles) {
                        synchronized (this.file_Cache) {
                            this.file_Cache.put(var1, var3);
                        }
                    }

                    return var3;
                }
            } catch (Exception var20) {
            }

            try {
                (var7 = (new URL(this.getCodeBase(), var1)).openConnection()).setUseCaches(true);

                try {
                    var4 = var7.getContentLength();
                } catch (Exception var16) {
                    var4 = 0;
                }

                var5 = var7.getInputStream();
                var3 = this.file_read(var5, var4, var2);
                var5.close();
                if (var3 != null) {
                    this.m3(var3, var1);
                    if (this.file_cachefiles) {
                        synchronized (this.file_Cache) {
                            this.file_Cache.put(var1, var3);
                        }
                    }

                    return var3;
                }
            } catch (Exception var17) {
            }

            try {
                if ((var5 = Class.forName("ptviewer").getResourceAsStream(var1)) != null) {
                    var3 = this.file_read(var5, 0, (int[]) null);
                    var5.close();
                }

                if (var3 != null) {
                    this.m3(var3, var1);
                    if (this.file_cachefiles) {
                        synchronized (this.file_Cache) {
                            this.file_Cache.put(var1, var3);
                        }
                    }

                    return var3;
                }
            } catch (Exception var14) {
            }

            return null;
        }
    }

    byte[] file_read(InputStream var1, int var2, int[] var3) {
        int var4 = 0;
        int var6 = 0;
        int var7 = var2 > 0 ? var2 / 10 + 1 : '썐';
        byte[] var8 = new byte[var2 > 0 ? var2 : '썐'];

        try {
            byte[] var9;
            while (var6 != -1) {
                int var5 = 0;
                if (var8.length < var4 + var7) {
                    var9 = new byte[var4 + var7];
                    System.arraycopy(var8, 0, var9, 0, var4);
                    var8 = var9;
                }

                while (var5 < var7 && (var6 = var1.read(var8, var4, var7 - var5)) != -1) {
                    var5 += var6;
                    var4 += var6;
                    if (var2 > 0 && var3 != null) {
                        var3[0] = (800 * var4 / var2 + 5) / 10;
                        if (var3[0] > 100) {
                            var3[0] = 100;
                        }

                        this.repaint();
                    }
                }
            }

            if (var8.length > var4) {
                var9 = new byte[var4];
                System.arraycopy(var8, 0, var9, 0, var4);
                var8 = var9;
            }

            return var8;
        } catch (Exception var11) {
            return null;
        }
    }

    private void m2(byte[] var1, byte[] var2) {
        int var3 = 0;

        int var4;
        for (var4 = 0; var3 < var1.length; ++var4) {
            if (var4 >= var2.length) {
                var4 = 0;
            }

            var1[var3] ^= var2[var4];
            ++var3;
        }

        int[] var5 = new int[]{1, 20, 3, 18, 0, 17, 14, 11, 22, 19, 2, 5, 7, 6, 13, 4, 21, 8, 10, 9, 12, 15, 16};
        int var6 = var1.length - var5.length;
        byte[] var7 = new byte[var5.length];

        for (var3 = 0; var3 < var6; var3 += var5.length) {
            System.arraycopy(var1, var3, var7, 0, var5.length);

            for (var4 = 0; var4 < var5.length; ++var4) {
                var1[var4 + var3] = var7[var5[var4]];
            }
        }

    }

    private void m3(byte[] var1, String var2) {
        if (var1 != null && var2 != null) {
            int var3;
            if ((var3 = var2.lastIndexOf(46)) >= 0 && var3 + 1 < var2.length()) {
                byte[] var4 = new byte[]{122, 1, 12, -78, -99, -33, -50, 17, 88, 90, -117, 119, 30, 20, 10, 33, 27, 114, 121, 3, -11, 51, 97, -59, -32, -28, 0, 83, 37, 43, -67, 17, 32, 31, 70, -70, -10, -39, -33, 2, 55, 59, -88};
                if (var2.substring(var3 + 1).equalsIgnoreCase("jpa")) {
                    this.m2(var1, var4);
                } else {
                    byte[] var5;
                    byte[] var6;
                    if (var2.substring(var3 + 1).equalsIgnoreCase("jpb")) {
                        var5 = this.m1().getBytes();
                        var6 = new byte[var4.length + var5.length];
                        System.arraycopy(var4, 0, var6, 0, var4.length);
                        System.arraycopy(var5, 0, var6, var4.length, var5.length);
                        this.m2(var1, var6);
                    } else {
                        if (var2.substring(var3 + 1).equalsIgnoreCase("jpc")) {
                            var5 = this.getDocumentBase().toString().getBytes();
                            var6 = new byte[var4.length + var5.length];
                            System.arraycopy(var4, 0, var6, 0, var4.length);
                            System.arraycopy(var5, 0, var6, var4.length, var5.length);
                            this.m2(var1, var6);
                        }

                    }
                }
            }
        }
    }

    void pb_reset() {
        this.percent[0] = 0;
    }

    void pb_init() {
        this.percent = new int[1];
        this.percent[0] = 0;
    }

    void pb_draw(Graphics var1, int var2, int var3) {
        if (this.pb_x == -1) {
            this.pb_x = var2 >> 2;
        }

        if (this.pb_y == -1) {
            this.pb_y = var3 * 3 >> 2;
        }

        if (this.pb_width == -1) {
            this.pb_width = var2 >> 1;
        }

        int var4 = 0;
        if (this.percent != null) {
            var4 = this.percent[0];
        }

        var1.setColor(this.pb_color);
        var1.fillRect(this.pb_x, this.pb_y, this.pb_width * var4 / 100, this.pb_height);
    }

    void shs_init() {
        this.shotspots = new Vector();
    }

    void shs_setup() {
        if (this.shotspots.size() > 0) {
            this.shs_allocate(this.shotspots.size());

            for (int var1 = 0; var1 < this.numshs; ++var1) {
                this.ParseStaticHotspotLine((String) this.shotspots.elementAt(var1), var1);
            }
        }

    }

    void shs_allocate(int var1) {
        try {
            this.shs_x1 = new int[var1];
            this.shs_x2 = new int[var1];
            this.shs_y1 = new int[var1];
            this.shs_y2 = new int[var1];
            this.shs_url = new String[var1];
            this.shs_target = new String[var1];
            this.shs_him = new Object[var1];
            this.shs_imode = new int[var1];
            this.shs_active = new boolean[var1];
            this.numshs = var1;
        } catch (Exception var3) {
            this.numshs = 0;
        }
    }

    void shs_dispose() {
        for (int var1 = 0; var1 < this.numshs; ++var1) {
            if (this.shs_him[var1] != null) {
                this.shs_him[var1] = null;
            }
        }

        this.numshs = 0;
    }

    void ParseStaticHotspotLine(String var1, int var2) {
        int var3 = 0;
        int var5 = var1.length();
        StringBuffer var6 = new StringBuffer();
        this.shs_x1[var2] = 0;
        this.shs_x2[var2] = 0;
        this.shs_y1[var2] = 0;
        this.shs_y2[var2] = 0;
        this.shs_url[var2] = null;
        this.shs_target[var2] = null;
        this.shs_him[var2] = null;
        this.shs_imode[var2] = 0;
        this.shs_active[var2] = false;

        while (true) {
            while (var3 < var5) {
                switch (var1.charAt(var3++)) {
                    case 'a':
                        var3 = this.getNextWord(var3, var1, var6);
                        this.shs_x2[var2] = Integer.parseInt(var6.toString());
                        break;
                    case 'b':
                        var3 = this.getNextWord(var3, var1, var6);
                        this.shs_y2[var2] = Integer.parseInt(var6.toString());
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'r':
                    case 's':
                    case 'v':
                    case 'w':
                    default:
                        break;
                    case 'i':
                        var3 = this.getNextWord(var3, var1, var6);
                        if (!var6.toString().startsWith("ptviewer:") && !var6.toString().startsWith("javascript:")) {
                            this.shs_him[var2] = this.loadImage(var6.toString());
                            break;
                        }

                        this.shs_him[var2] = var6.toString();
                        break;
                    case 'p':
                        this.shs_imode[var2] = 1;
                        break;
                    case 'q':
                        this.shs_imode[var2] = 2;
                        break;
                    case 't':
                        var3 = this.getNextWord(var3, var1, var6);
                        this.shs_target[var2] = var6.toString();
                        break;
                    case 'u':
                        var3 = this.getNextWord(var3, var1, var6);
                        this.shs_url[var2] = var6.toString();
                        break;
                    case 'x':
                        var3 = this.getNextWord(var3, var1, var6);
                        this.shs_x1[var2] = Integer.parseInt(var6.toString());
                        break;
                    case 'y':
                        var3 = this.getNextWord(var3, var1, var6);
                        this.shs_y1[var2] = Integer.parseInt(var6.toString());
                }
            }

            return;
        }
    }

    final void shs_draw(Graphics var1) {
        for (int var2 = 0; var2 < this.numshs; ++var2) {
            if (this.shs_him[var2] != null) {
                if (((this.shs_imode[var2] & 2) > 0 || this.shs_active[var2] && (this.shs_imode[var2] & 1) > 0) && this.shs_him[var2] instanceof Image) {
                    var1.drawImage((Image) this.shs_him[var2], this.shs_x1[var2], this.shs_y1[var2], this);
                }

                if (this.shs_him[var2] instanceof String && this.shs_active[var2]) {
                    this.JumpToLink((String) this.shs_him[var2], (String) null);
                }
            }
        }

    }

    final int OverStaticHotspot(int var1, int var2) {
        int var4 = -1;

        for (int var3 = 0; var3 < this.numshs; ++var3) {
            if (this.shs_url[var3] == null || var1 < this.shs_x1[var3] || var1 > this.shs_x2[var3] || (var2 < this.shs_y1[var3] || var2 > this.shs_y2[var3]) && (var2 < this.shs_y2[var3] || var2 > this.shs_y1[var3])) {
                this.shs_active[var3] = false;
            } else {
                this.shs_active[var3] = true;
                if (var3 > var4) {
                    var4 = var3;
                }
            }
        }

        return var4;
    }

    void math_init() {
        this.f1 = new double[3][3];
        this.f2 = new int[3][3];
    }

    void math_dispose() {
        this.atan_LU_HR = null;
        this.sqrt_LU = null;
        this.f1 = null;
        this.f2 = null;
    }

    final void math_setLookUp(int[][] var1) {
        if (var1 != null) {
            if (this.atan_LU_HR == null) {
                this.atan_LU_HR = new int[4097];
                this.atan_LU = new double[4097];
                this.sqrt_LU = new int[4097];
                double var6 = 2.44140625E-4D;
                double var4 = 0.0D;

                int var2;
                for (var2 = 0; var2 < 4096; var4 += var6) {
                    this.sqrt_LU[var2] = (int) (Math.sqrt(1.0D + var4 * var4) * 4096.0D);
                    ++var2;
                }

                this.sqrt_LU[4096] = (int) (Math.sqrt(2.0D) * 4096.0D);
                var6 = 2.44140625E-4D;
                var4 = 0.0D;

                for (var2 = 0; var2 < 4097; var4 += var6) {
                    if (var2 < 4096) {
                        this.atan_LU[var2] = Math.atan(var4 / (1.0D - var4)) * 256.0D;
                    } else {
                        this.atan_LU[var2] = 402.1238596594935D;
                    }

                    ++var2;
                }
            }

            this.math_updateLookUp(var1[0].length);
        }

    }

    final void math_updateLookUp(int var1) {
        int var2 = var1 << 6;
        if (this.PV_atan0_HR != var2) {
            double var3 = (double) var1 / 6.283185307179586D;
            this.PV_atan0_HR = var2;
            this.PV_pi_HR = 128 * var1;

            for (int var5 = 0; var5 < 4097; ++var5) {
                this.atan_LU_HR[var5] = (int) (var3 * this.atan_LU[var5] + 0.5D);
            }
        }

    }

    final void SetMatrix(double var1, double var3, double[][] var5, int var6) {
        double[][] var7 = new double[3][3];
        double[][] var8 = new double[3][3];
        var7[0][0] = 1.0D;
        var7[0][1] = 0.0D;
        var7[0][2] = 0.0D;
        var7[1][0] = 0.0D;
        var7[1][1] = Math.cos(var1);
        var7[1][2] = Math.sin(var1);
        var7[2][0] = 0.0D;
        var7[2][1] = -var7[1][2];
        var7[2][2] = var7[1][1];
        var8[0][0] = Math.cos(var3);
        var8[0][1] = 0.0D;
        var8[0][2] = -Math.sin(var3);
        var8[1][0] = 0.0D;
        var8[1][1] = 1.0D;
        var8[1][2] = 0.0D;
        var8[2][0] = -var8[0][2];
        var8[2][1] = 0.0D;
        var8[2][2] = var8[0][0];
        if (var6 == 1) {
            this.matrix_matrix_mult(var7, var8, var5);
        } else {
            this.matrix_matrix_mult(var8, var7, var5);
        }
    }

    final void matrix_matrix_mult(double[][] var1, double[][] var2, double[][] var3) {
        for (int var4 = 0; var4 < 3; ++var4) {
            for (int var5 = 0; var5 < 3; ++var5) {
                var3[var4][var5] = var1[var4][0] * var2[0][var5] + var1[var4][1] * var2[1][var5] + var1[var4][2] * var2[2][var5];
            }
        }

    }

    final int PV_atan2_HR(int var1, int var2) {
        if (var2 > 0) {
            return var1 > 0 ? this.atan_LU_HR[4096 * var1 / (var2 + var1)] : -this.atan_LU_HR[4096 * -var1 / (var2 - var1)];
        } else if (var2 == 0) {
            return var1 > 0 ? this.PV_atan0_HR : -this.PV_atan0_HR;
        } else {
            return var1 < 0 ? this.atan_LU_HR[4096 * var1 / (var2 + var1)] - this.PV_pi_HR : -this.atan_LU_HR[4096 * -var1 / (var2 - var1)] + this.PV_pi_HR;
        }
    }

    final int PV_sqrt(int var1, int var2) {
        if (var1 > var2) {
            return var1 * this.sqrt_LU[(var2 << 12) / var1] >> 12;
        } else {
            return var2 == 0 ? 0 : var2 * this.sqrt_LU[(var1 << 12) / var2] >> 12;
        }
    }

    static final int bil(int var0, int var1, int var2, int var3, int var4, int var5) {
        int var6 = 255 - var4;
        int var7 = 255 - var5;
        int var8 = var6 * var7;
        int var9 = var5 * var6;
        int var10 = var4 * var5;
        int var11 = var4 * var7;
        int var12 = var8 * (var0 >> 16 & 255) + var11 * (var1 >> 16 & 255) + var9 * (var2 >> 16 & 255) + var10 * (var3 >> 16 & 255) & 16711680;
        int var13 = var8 * (var0 >> 8 & 255) + var11 * (var1 >> 8 & 255) + var9 * (var2 >> 8 & 255) + var10 * (var3 >> 8 & 255) >> 16;
        int var14 = var8 * (var0 & 255) + var11 * (var1 & 255) + var9 * (var2 & 255) + var10 * (var3 & 255) >> 16;
        return var12 + (var13 << 8) + var14 + -16777216;
    }

    final void math_extractview(int[][] var1, int[] var2, byte[] var3, int var4, double var5, double var7, double var9, boolean var11) {
        this.math_set_int_matrix(var5, var7, var9, var4);
        this.math_transform(var1, var1[0].length, var1.length, var2, var3, var4, var2.length / var4, var11);
    }

    final void math_set_int_matrix(double var1, double var3, double var5, int var7) {
        double var8 = var1 * 2.0D * 3.141592653589793D / 360.0D;
        double var10 = (double) var7 / (2.0D * Math.tan(var8 / 2.0D));
        this.SetMatrix(var5 * 2.0D * 3.141592653589793D / 360.0D, var3 * 2.0D * 3.141592653589793D / 360.0D, this.f1, 1);
        double[] var10000 = this.f1[0];
        var10000[0] /= var10;
        var10000 = this.f1[0];
        var10000[1] /= var10;
        var10000 = this.f1[0];
        var10000[2] /= var10;
        var10000 = this.f1[1];
        var10000[0] /= var10;
        var10000 = this.f1[1];
        var10000[1] /= var10;
        var10000 = this.f1[1];
        var10000[2] /= var10;
        double var12 = var8 > 0.3D ? 131072.0D / var8 : 436906.6666666667D;

        for (int var14 = 0; var14 < 3; ++var14) {
            for (int var15 = 0; var15 < 3; ++var15) {
                this.f2[var14][var15] = (int) (var12 * this.f1[var14][var15] + 0.5D);
            }
        }

    }

    final void math_transform(int[][] var1, int var2, int var3, int[] var4, byte[] var5, int var6, int var7, boolean var8) {
        int var18 = var2 - 1;
        int var19 = var3 - 1;
        int var20 = var6 - 1 >> 1;
        int var21 = var7 >> 1;
        int var22 = var2 >> 1;
        int var23 = var3 >> 1;
        int var24 = 128 * var2 + 128;
        int var25 = 128 * var3 + 128;
        int var29 = -var20;
        int var30 = var6 - var20;
        int var31 = -var21;
        int var32 = var7 - var21;
        int var11 = 0;
        int var9;
        int var10;
        int var12;
        int var13;
        int var14;
        int var15;
        int var16;
        int var17;
        int var26;
        int var27;
        int var28;
        int var33;
        int var35;
        int var36;
        int var37;
        int var42;
        int var43;
        if (!var8) {
            var33 = 0;
            int[] var55 = var1[0];
            var13 = this.f2[1][0] * var31 + this.f2[2][0];
            var14 = this.f2[1][1] * var31 + this.f2[2][1];
            var15 = this.f2[1][2] * var31 + this.f2[2][2];
            var36 = this.f2[0][0];
            var37 = this.f2[0][2];
            double var54 = this.math_fovy(this.hfov, var6, var7) / 2.0D;
            if (this.pitch + var54 <= 80.0D && this.pitch - var54 >= -80.0D) {
                boolean var58 = true;
                int var59 = var6 >> 1;
                if (2 * var59 == var6) {
                    var58 = false;
                }

                for (var10 = var31; var10 < var32; var15 += this.f2[1][2]) {
                    var27 = var14;
                    int var56;
                    if (!var58) {
                        var26 = var13 + (var36 >> 1);
                        var28 = var15 + (var37 >> 1);
                        var42 = 2 * this.PV_atan2_HR(var26, var28);
                        var12 = (var56 = var11 - var29) + 1;
                    } else {
                        var16 = this.PV_atan2_HR(var13, var15);
                        var42 = 2 * var16;
                        var12 = var11 - var29;
                        if (var4[var12] == 0) {
                            var17 = this.PV_atan2_HR(var14, this.PV_sqrt(Math.abs(var15), Math.abs(var13)));
                            var16 = var16 + var24 >> 8;
                            if ((var17 = var17 + var25 >> 8) >= 0 && var17 < var19 && var16 >= 0 && var16 < var18) {
                                var33 = var17;
                                var35 = (var55 = var1[var17])[var16];
                            } else {
                                if (var17 < 0) {
                                    var55 = var1[0];
                                    var33 = 0;
                                } else if (var17 > var19) {
                                    var55 = var1[var19];
                                    var33 = var19;
                                } else {
                                    var55 = var1[var17];
                                    var33 = var17;
                                }

                                if (var16 < 0) {
                                    var35 = var55[var18];
                                } else if (var16 > var18) {
                                    var35 = var55[0];
                                } else {
                                    var35 = var55[var16];
                                }
                            }

                            var4[var12] = var35 | -16777216;
                            var5[var12] = (byte) (var35 >> 24);
                        }

                        var56 = var12++ - 1;
                    }

                    var26 = var13 + var36;
                    var28 = var15 + var37;

                    for (var9 = 1; var9 < var30; var28 += var37) {
                        int var45 = var4[var12];
                        int var57 = var4[var56];
                        if (var45 == 0 || var57 == 0) {
                            var16 = this.PV_atan2_HR(var26, var28);
                            var43 = var42 - var16;
                            var17 = this.PV_atan2_HR(var27, this.PV_sqrt(Math.abs(var28), Math.abs(var26)));
                            var16 = var16 + var24 >> 8;
                            if ((var17 = var17 + var25 >> 8) == var33 && var16 >= 0 && var16 < var18) {
                                var35 = var55[var16];
                            } else if (var17 >= 0 && var17 < var19 && var16 >= 0 && var16 < var18) {
                                var33 = var17;
                                var35 = (var55 = var1[var17])[var16];
                            } else {
                                if (var17 < 0) {
                                    var55 = var1[0];
                                    var33 = 0;
                                } else if (var17 > var19) {
                                    var55 = var1[var19];
                                    var33 = var19;
                                } else {
                                    var55 = var1[var17];
                                    var33 = var17;
                                }

                                if (var16 < 0) {
                                    var35 = var55[var18];
                                } else if (var16 > var18) {
                                    var35 = var55[0];
                                } else {
                                    var35 = var55[var16];
                                }
                            }

                            if (var45 == 0) {
                                var4[var12] = var35 | -16777216;
                                var5[var12] = (byte) (var35 >> 24);
                            }

                            if (var57 == 0) {
                                if ((var16 = var43 + var24 >> 8) < 0) {
                                    var16 += var2;
                                } else if (var16 > var18) {
                                    var16 -= var2;
                                }

                                if (var16 < var18) {
                                    var35 = var55[var16];
                                } else if (var16 > var18) {
                                    var35 = var55[0];
                                } else {
                                    var35 = var55[var16];
                                }

                                var4[var56] = var35 | -16777216;
                                var5[var56] = (byte) (var35 >> 24);
                            }
                        }

                        ++var9;
                        ++var12;
                        --var56;
                        var26 += var36;
                    }

                    ++var10;
                    var11 += var6;
                    var13 += this.f2[1][0];
                    var14 += this.f2[1][1];
                }

            } else {
                for (var10 = var31; var10 < var32; var15 += this.f2[1][2]) {
                    var12 = var11;
                    var26 = var13 + var29 * var36;
                    var27 = var14;
                    var28 = var15 + var29 * var37;

                    for (var9 = var29; var9 < var30; var28 += var37) {
                        if (var4[var12] == 0) {
                            var16 = this.PV_atan2_HR(var26, var28);
                            var17 = this.PV_atan2_HR(var27, this.PV_sqrt(Math.abs(var28), Math.abs(var26)));
                            var16 = var16 + var24 >> 8;
                            if ((var17 = var17 + var25 >> 8) == var33 && var16 >= 0 && var16 < var18) {
                                var35 = var55[var16];
                            } else if (var17 >= 0 && var17 < var19 && var16 >= 0 && var16 < var18) {
                                var33 = var17;
                                var35 = (var55 = var1[var17])[var16];
                            } else {
                                if (var17 < 0) {
                                    var55 = var1[0];
                                    var33 = 0;
                                } else if (var17 > var19) {
                                    var55 = var1[var19];
                                    var33 = var19;
                                } else {
                                    var55 = var1[var17];
                                    var33 = var17;
                                }

                                if (var16 < 0) {
                                    var35 = var55[var18];
                                } else if (var16 > var18) {
                                    var35 = var55[0];
                                } else {
                                    var35 = var55[var16];
                                }
                            }

                            var4[var12] = var35 | -16777216;
                            var5[var12] = (byte) (var35 >> 24);
                        }

                        ++var9;
                        ++var12;
                        var26 += var36;
                    }

                    ++var10;
                    var11 += var6;
                    var13 += this.f2[1][0];
                    var14 += this.f2[1][1];
                }

            }
        } else {
            int var39 = 0;
            int[] var40 = var1[0];
            int[] var41 = var1[1];
            var13 = this.f2[1][0] * var31 + this.f2[2][0];
            var14 = this.f2[1][1] * var31 + this.f2[2][1];
            var15 = this.f2[1][2] * var31 + this.f2[2][2];
            var42 = this.f2[0][0];
            var43 = this.f2[0][2];
            double var44 = this.math_fovy(this.hfov, var6, var7) / 2.0D;
            int var34;
            int var38;
            if (this.pitch + var44 <= 80.0D && this.pitch - var44 >= -80.0D) {
                boolean var46 = true;
                int var47 = var6 >> 1;
                if (2 * var47 == var6) {
                    var46 = false;
                }

                for (var10 = var31; var10 < var32; var15 += this.f2[1][2]) {
                    var27 = var14;
                    int var48;
                    int var50;
                    if (!var46) {
                        var26 = var13 + (var42 >> 1);
                        var28 = var15 + (var43 >> 1);
                        var48 = 2 * this.PV_atan2_HR(var26, var28);
                        var12 = (var50 = var11 - var29) + 1;
                    } else {
                        var16 = this.PV_atan2_HR(var13, var15);
                        var48 = 2 * var16;
                        var12 = var11 - var29;
                        if (var4[var12] == 0) {
                            var17 = this.PV_atan2_HR(var14, this.PV_sqrt(Math.abs(var15), Math.abs(var13)));
                            var37 = var16 & 255;
                            var38 = var17 & 255;
                            var16 = (var16 >> 8) + var22;
                            if ((var17 = (var17 >> 8) + var23) >= 0 && var17 < var19 && var16 >= 0 && var16 < var18) {
                                var39 = var17;
                                var40 = var1[var17];
                                var41 = var1[var17 + 1];
                                var33 = var40[var16];
                                var35 = var41[var16++];
                                var34 = var40[var16];
                                var36 = var41[var16];
                            } else {
                                if (var17 < 0) {
                                    var40 = var1[0];
                                    var39 = 0;
                                } else if (var17 > var19) {
                                    var40 = var1[var19];
                                    var39 = var19;
                                } else {
                                    var40 = var1[var17];
                                    var39 = var17;
                                }

                                ++var17;
                                if (var17 < 0) {
                                    var41 = var1[0];
                                } else if (var17 > var19) {
                                    var41 = var1[var19];
                                } else {
                                    var41 = var1[var17];
                                }

                                if (var16 < 0) {
                                    var33 = var40[var18];
                                    var35 = var41[var18];
                                } else if (var16 > var18) {
                                    var33 = var40[0];
                                    var35 = var41[0];
                                } else {
                                    var33 = var40[var16];
                                    var35 = var41[var16];
                                }

                                ++var16;
                                if (var16 < 0) {
                                    var34 = var40[var18];
                                    var36 = var41[var18];
                                } else if (var16 > var18) {
                                    var34 = var40[0];
                                    var36 = var41[0];
                                } else {
                                    var34 = var40[var16];
                                    var36 = var41[var16];
                                }
                            }

                            var4[var12] = bil(var33, var34, var35, var36, var37, var38);
                            var5[var12] = (byte) (var33 >> 24);
                        }

                        var50 = var12++ - 1;
                    }

                    var26 = var13 + var42;
                    var28 = var15 + var43;

                    for (var9 = 1; var9 < var30; var28 += var43) {
                        int var51 = var4[var12];
                        int var52 = var4[var50];
                        if (var51 == 0 || var52 == 0) {
                            var16 = this.PV_atan2_HR(var26, var28);
                            int var49 = var48 - var16;
                            var17 = this.PV_atan2_HR(var27, this.PV_sqrt(Math.abs(var28), Math.abs(var26)));
                            var37 = var16 & 255;
                            var38 = var17 & 255;
                            var16 = (var16 >> 8) + var22;
                            if ((var17 = (var17 >> 8) + var23) == var39 && var16 >= 0 && var16 < var18) {
                                var33 = var40[var16];
                                var35 = var41[var16++];
                                var34 = var40[var16];
                                var36 = var41[var16];
                            } else if (var17 >= 0 && var17 < var19 && var16 >= 0 && var16 < var18) {
                                var39 = var17;
                                var40 = var1[var17];
                                var41 = var1[var17 + 1];
                                var33 = var40[var16];
                                var35 = var41[var16++];
                                var34 = var40[var16];
                                var36 = var41[var16];
                            } else {
                                if (var17 < 0) {
                                    var40 = var1[0];
                                    var39 = 0;
                                } else if (var17 > var19) {
                                    var40 = var1[var19];
                                    var39 = var19;
                                } else {
                                    var40 = var1[var17];
                                    var39 = var17;
                                }

                                ++var17;
                                if (var17 < 0) {
                                    var41 = var1[0];
                                } else if (var17 > var19) {
                                    var41 = var1[var19];
                                } else {
                                    var41 = var1[var17];
                                }

                                if (var16 < 0) {
                                    var33 = var40[var18];
                                    var35 = var41[var18];
                                } else if (var16 > var18) {
                                    var33 = var40[0];
                                    var35 = var41[0];
                                } else {
                                    var33 = var40[var16];
                                    var35 = var41[var16];
                                }

                                ++var16;
                                if (var16 < 0) {
                                    var34 = var40[var18];
                                    var36 = var41[var18];
                                } else if (var16 > var18) {
                                    var34 = var40[0];
                                    var36 = var41[0];
                                } else {
                                    var34 = var40[var16];
                                    var36 = var41[var16];
                                }
                            }

                            if (var51 == 0) {
                                var4[var12] = bil(var33, var34, var35, var36, var37, var38);
                                var5[var12] = (byte) (var33 >> 24);
                            }

                            if (var52 == 0) {
                                var37 = var49 & 255;
                                if ((var16 = (var49 >> 8) + var22) < 0) {
                                    var16 += var2;
                                } else if (var16 > var18) {
                                    var16 -= var2;
                                }

                                if (var16 < var18) {
                                    var33 = var40[var16];
                                    var35 = var41[var16++];
                                    var34 = var40[var16];
                                    var36 = var41[var16];
                                } else {
                                    if (var16 > var18) {
                                        var33 = var40[0];
                                        var35 = var41[0];
                                    } else {
                                        var33 = var40[var16];
                                        var35 = var41[var16];
                                    }

                                    ++var16;
                                    if (var16 > var18) {
                                        var34 = var40[0];
                                        var36 = var41[0];
                                    } else {
                                        var34 = var40[var16];
                                        var36 = var41[var16];
                                    }
                                }

                                var4[var50] = bil(var33, var34, var35, var36, var37, var38);
                                var5[var50] = (byte) (var33 >> 24);
                            }
                        }

                        ++var9;
                        ++var12;
                        --var50;
                        var26 += var42;
                    }

                    ++var10;
                    var11 += var6;
                    var13 += this.f2[1][0];
                    var14 += this.f2[1][1];
                }

            } else {
                for (var10 = var31; var10 < var32; var15 += this.f2[1][2]) {
                    var12 = var11;
                    var26 = var13 + var29 * var42;
                    var27 = var14;
                    var28 = var15 + var29 * var43;

                    for (var9 = var29; var9 < var30; var28 += var43) {
                        if (var4[var12] == 0) {
                            var16 = this.PV_atan2_HR(var26, var28);
                            var17 = this.PV_atan2_HR(var27, this.PV_sqrt(Math.abs(var28), Math.abs(var26)));
                            var37 = var16 & 255;
                            var38 = var17 & 255;
                            var16 = (var16 >> 8) + var22;
                            if ((var17 = (var17 >> 8) + var23) == var39 && var16 >= 0 && var16 < var18) {
                                var33 = var40[var16];
                                var35 = var41[var16++];
                                var34 = var40[var16];
                                var36 = var41[var16];
                            } else if (var17 >= 0 && var17 < var19 && var16 >= 0 && var16 < var18) {
                                var39 = var17;
                                var40 = var1[var17];
                                var41 = var1[var17 + 1];
                                var33 = var40[var16];
                                var35 = var41[var16++];
                                var34 = var40[var16];
                                var36 = var41[var16];
                            } else {
                                if (var17 < 0) {
                                    var40 = var1[0];
                                    var39 = 0;
                                } else if (var17 > var19) {
                                    var40 = var1[var19];
                                    var39 = var19;
                                } else {
                                    var40 = var1[var17];
                                    var39 = var17;
                                }

                                ++var17;
                                if (var17 < 0) {
                                    var41 = var1[0];
                                } else if (var17 > var19) {
                                    var41 = var1[var19];
                                } else {
                                    var41 = var1[var17];
                                }

                                if (var16 < 0) {
                                    var33 = var40[var18];
                                    var35 = var41[var18];
                                } else if (var16 > var18) {
                                    var33 = var40[0];
                                    var35 = var41[0];
                                } else {
                                    var33 = var40[var16];
                                    var35 = var41[var16];
                                }

                                ++var16;
                                if (var16 < 0) {
                                    var34 = var40[var18];
                                    var36 = var41[var18];
                                } else if (var16 > var18) {
                                    var34 = var40[0];
                                    var36 = var41[0];
                                } else {
                                    var34 = var40[var16];
                                    var36 = var41[var16];
                                }
                            }

                            var4[var12] = bil(var33, var34, var35, var36, var37, var38);
                            var5[var12] = (byte) (var33 >> 24);
                        }

                        ++var9;
                        ++var12;
                        var26 += var42;
                    }

                    ++var10;
                    var11 += var6;
                    var13 += this.f2[1][0];
                    var14 += this.f2[1][1];
                }

            }
        }
    }

    final double[] math_view2pano(int var1, int var2, int var3, int var4, int var5, int var6, double var7, double var9, double var11) {
        double var27 = (double) var5 / 6.283185307179586D;
        double var13 = var11 * 2.0D * 3.141592653589793D / 360.0D;
        double var17 = (double) ((int) ((double) var3 / (2.0D * Math.tan(var13 / 2.0D)) + 0.5D));
        this.SetMatrix(var9 * 2.0D * 3.141592653589793D / 360.0D, var7 * 2.0D * 3.141592653589793D / 360.0D, this.f1, 1);
        var1 -= var3 >> 1;
        var2 -= var4 >> 1;
        double var21 = this.f1[0][0] * (double) var1 + this.f1[1][0] * (double) var2 + this.f1[2][0] * var17;
        double var23 = this.f1[0][1] * (double) var1 + this.f1[1][1] * (double) var2 + this.f1[2][1] * var17;
        double var25 = this.f1[0][2] * (double) var1 + this.f1[1][2] * (double) var2 + this.f1[2][2] * var17;
        double[] var29;
        (var29 = new double[2])[0] = var27 * Math.atan2(var21, var25) + (double) var5 / 2.0D;
        var29[1] = var27 * Math.atan2(var23, Math.sqrt(var25 * var25 + var21 * var21)) + (double) var6 / 2.0D;
        return var29;
    }

    final int[] math_int_view2pano(int var1, int var2, int var3, int var4, int var5, int var6, double var7, double var9, double var11) {
        double[] var13;
        if ((var13 = this.math_view2pano(var1, var2, var3, var4, var5, var6, var7, var9, var11))[0] < 0.0D) {
            var13[0] = 0.0D;
        }

        if (var13[0] >= (double) var5) {
            var13[0] = (double) (var5 - 1);
        }

        if (var13[1] < 0.0D) {
            var13[1] = 0.0D;
        }

        if (var13[1] >= (double) var6) {
            var13[1] = (double) (var6 - 1);
        }

        int[] var14;
        (var14 = new int[2])[0] = (int) var13[0];
        var14[1] = (int) var13[1];
        return var14;
    }

    final double math_fovy(double var1, int var3, int var4) {
        return 114.59155902616465D * Math.atan((double) var4 / (double) var3 * Math.tan(var1 / 2.0D * 3.141592653589793D / 180.0D));
    }

    void roi_allocate(int var1) {
        try {
            this.roi_im = new String[var1];
            this.roi_xp = new int[var1];
            this.roi_yp = new int[var1];
            this.roi_loaded = new boolean[var1];
            this.numroi = var1;
        } catch (Exception var3) {
            this.numroi = 0;
        }
    }

    void roi_dispose() {
        for (int var1 = 0; var1 < this.numroi; ++var1) {
            this.roi_im[var1] = null;
        }

        this.roi_im = null;
        this.roi_xp = null;
        this.roi_yp = null;
        this.roi_loaded = null;
        this.numroi = 0;
    }

    void ParseROILine(String var1, int var2) {
        int var3 = 0;
        int var5 = var1.length();
        StringBuffer var6 = new StringBuffer();
        this.roi_im[var2] = null;
        this.roi_xp[var2] = 0;
        this.roi_yp[var2] = 0;
        this.roi_loaded[var2] = false;

        while (var3 < var5) {
            switch (var1.charAt(var3++)) {
                case 'i':
                    var3 = this.getNextWord(var3, var1, var6);
                    this.roi_im[var2] = var6.toString();
                    break;
                case 'x':
                    var3 = this.getNextWord(var3, var1, var6);
                    this.roi_xp[var2] = Integer.parseInt(var6.toString());
                    break;
                case 'y':
                    var3 = this.getNextWord(var3, var1, var6);
                    this.roi_yp[var2] = Integer.parseInt(var6.toString());
            }
        }

    }

    void snd_init() {
        this.sounds = new Vector();
    }

    void snd_dispose() {
        this.sounds.removeAllElements();
    }

    public synchronized void PlaySound(int var1) {
        if (var1 < this.sounds.size() && this.sounds.elementAt(var1) != null && this.sounds.elementAt(var1) instanceof AudioClip) {
            ((AudioClip) this.sounds.elementAt(var1)).play();
        }

    }

    void SetupSounds() {
        for (int var1 = 0; var1 < this.sounds.size(); ++var1) {
            if (this.sounds.elementAt(var1) != null && this.sounds.elementAt(var1) instanceof String) {
                String var2 = (String) this.sounds.elementAt(var1);

                try {
                    URL var3 = new URL(this.getDocumentBase(), var2);
                    this.sounds.setElementAt(this.getAudioClip(var3), var1);
                } catch (Exception var8) {
                    try {
                        URL var5 = Class.forName("ptviewer").getResource(var2);
                        this.sounds.setElementAt(this.getAudioClip(var5), var1);
                    } catch (Exception var7) {
                        this.sounds.setElementAt((Object) null, var1);
                    }
                }
            }
        }

    }

    void app_init() {
        this.applets = new Hashtable();
        this.app_properties = new Vector();
    }

    public void startApplet(int var1) {
        if (var1 >= 0 && this.app_properties != null && var1 < this.app_properties.size() && this.app_properties.elementAt(var1) != null) {
            if (this.applets.get(this.app_properties.elementAt(var1)) != null) {
                this.stopApplet(var1);
            }

            try {
                String var2;
                Applet var5 = (Applet) Class.forName((var2 = this.myGetParameter((String) this.app_properties.elementAt(var1), "code")).substring(0, var2.lastIndexOf(".class"))).getConstructor(Class.forName("ptviewer"), class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String).newInstance(this, this.app_properties.elementAt(var1));
                this.applets.put(this.app_properties.elementAt(var1), var5);
                var5.init();
                var5.start();
            } catch (Exception var12) {
                try {
                    String var3;
                    Applet var6 = (Applet) Class.forName((var3 = this.myGetParameter((String) this.app_properties.elementAt(var1), "code")).substring(0, var3.lastIndexOf(".class"))).getConstructor().newInstance();
                    this.applets.put(this.app_properties.elementAt(var1), var6);
                    AppletStub var9 = (AppletStub) Class.forName("ptstub").getConstructor(Class.forName("ptviewer"), class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String).newInstance(this, this.app_properties.elementAt(var1));
                    var6.setStub(var9);
                    var6.init();
                    var6.start();
                } catch (Exception var11) {
                }
            }
        }
    }

    public void stopApplet(int var1) {
        if (var1 >= 0 && this.app_properties != null && var1 < this.app_properties.size() && this.app_properties.elementAt(var1) != null) {
            Applet var2;
            if ((var2 = (Applet) this.applets.get(this.app_properties.elementAt(var1))) != null) {
                var2.stop();
                this.applets.remove(this.app_properties.elementAt(var1));
            }

        }
    }

    void stopApplets(int var1) {
        for (int var2 = var1; var2 < this.app_properties.size(); ++var2) {
            this.stopApplet(var2);
        }

    }

    void hs_init() {
        this.hotspots = new Vector();
    }

    void hs_allocate(int var1) {
        try {
            this.hs_xp = new double[var1];
            this.hs_yp = new double[var1];
            this.hs_up = new double[var1];
            this.hs_vp = new double[var1];
            this.hs_xv = new int[var1];
            this.hs_yv = new int[var1];
            this.hs_hc = new Color[var1];
            this.hs_name = new String[var1];
            this.hs_url = new String[var1];
            this.hs_target = new String[var1];
            this.hs_him = new Object[var1];
            this.hs_visible = new boolean[var1];
            this.hs_imode = new int[var1];
            this.hs_mask = new String[var1];
            this.hs_link = new int[var1];
            this.numhs = var1;
        } catch (Exception var3) {
            this.numhs = 0;
        }
    }

    void hs_dispose() {
        for (int var1 = 0; var1 < this.numhs; ++var1) {
            if (this.hs_him[var1] != null) {
                this.hs_him[var1] = null;
            }

            this.hs_hc[var1] = null;
            this.hs_name[var1] = null;
            this.hs_url[var1] = null;
            this.hs_target[var1] = null;
            this.hs_mask[var1] = null;
        }

        this.numhs = 0;
        this.hotspots.removeAllElements();
        this.hs_xp = null;
        this.hs_yp = null;
        this.hs_up = null;
        this.hs_vp = null;
        this.hs_xv = null;
        this.hs_yv = null;
        this.hs_hc = null;
        this.hs_name = null;
        this.hs_url = null;
        this.hs_him = null;
        this.hs_visible = null;
        this.hs_target = null;
        this.hs_mask = null;
        this.hs_imode = null;
        this.hs_link = null;
        this.hs_image = null;
    }

    void ParseHotspotLine(String var1, int var2) {
        int var3 = 0;
        int var4 = var1.length();
        StringBuffer var5 = new StringBuffer();
        this.hs_xp[var2] = 0.0D;
        this.hs_yp[var2] = 0.0D;
        this.hs_up[var2] = -200.0D;
        this.hs_vp[var2] = -200.0D;
        this.hs_xv[var2] = 0;
        this.hs_yv[var2] = 0;
        this.hs_hc[var2] = null;
        this.hs_name[var2] = null;
        this.hs_url[var2] = null;
        this.hs_target[var2] = null;
        this.hs_him[var2] = null;
        this.hs_visible[var2] = false;
        this.hs_imode[var2] = 0;
        this.hs_mask[var2] = null;
        this.hs_link[var2] = -1;

        while (var3 < var4) {
            int[] var10000;
            switch (var1.charAt(var3++)) {
                case 'A':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_up[var2] = -Double.valueOf(var5.toString());
                    break;
                case 'B':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_vp[var2] = -Double.valueOf(var5.toString());
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'Z':
                case '[':
                case '\\':
                case ']':
                case '^':
                case '_':
                case '`':
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'j':
                case 'k':
                case 'l':
                case 'o':
                case 'r':
                case 's':
                case 'v':
                default:
                    break;
                case 'X':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_xp[var2] = -Double.valueOf(var5.toString());
                    break;
                case 'Y':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_yp[var2] = -Double.valueOf(var5.toString());
                    break;
                case 'a':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_up[var2] = Double.valueOf(var5.toString());
                    break;
                case 'b':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_vp[var2] = Double.valueOf(var5.toString());
                    break;
                case 'c':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_hc[var2] = new Color(Integer.parseInt(var5.toString(), 16));
                    break;
                case 'e':
                    var10000 = this.hs_imode;
                    var10000[var2] |= 16;
                    break;
                case 'i':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_him[var2] = var5.toString();
                    break;
                case 'm':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_mask[var2] = var5.toString();
                    break;
                case 'n':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_name[var2] = var5.toString();
                    break;
                case 'p':
                    var10000 = this.hs_imode;
                    var10000[var2] |= 1;
                    break;
                case 'q':
                    var10000 = this.hs_imode;
                    var10000[var2] |= 2;
                    break;
                case 't':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_target[var2] = var5.toString();
                    break;
                case 'u':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_url[var2] = var5.toString();
                    break;
                case 'w':
                    var10000 = this.hs_imode;
                    var10000[var2] |= 4;
                    break;
                case 'x':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_xp[var2] = Double.valueOf(var5.toString());
                    break;
                case 'y':
                    var3 = this.getNextWord(var3, var1, var5);
                    this.hs_yp[var2] = Double.valueOf(var5.toString());
            }
        }

    }

    void hs_read() {
        if (this.hotspots.size() != 0) {
            this.hs_allocate(this.hotspots.size());

            for (int var1 = 0; var1 < this.numhs; ++var1) {
                this.ParseHotspotLine((String) this.hotspots.elementAt(var1), var1);
            }

            this.hs_setLinkedHotspots();
        }

    }

    void hs_setup(int[][] var1) {
        if (var1 != null) {
            int var2 = var1.length;
            int var3 = var1[0].length;
            this.hs_read();

            int var5;
            for (var5 = 0; var5 < this.numhs; ++var5) {
                String var10;
                if (this.hs_him[var5] != null && (this.hs_imode[var5] & 16) == 0 && !(var10 = (String) this.hs_him[var5]).startsWith("ptviewer:") && !var10.startsWith("javascript:")) {
                    this.hs_him[var5] = this.loadImage(var10);
                }
            }

            this.hs_rel2abs(var3, var2);
            if (this.hs_image != null) {
                this.hs_image = this.loadImage((String) this.hs_image);
            }

            PixelGrabber var4;
            int var11;
            int var12;
            int var13;
            int var14;
            Image var19;
            if (this.hs_image != null && this.hs_image instanceof Image && var3 == ((Image) this.hs_image).getWidth((ImageObserver) null) && var2 == ((Image) this.hs_image).getHeight((ImageObserver) null)) {
                this.ptImageToAlpha(var1, (Image) this.hs_image);
            } else {
                for (var5 = 0; var5 < this.numhs && var5 < 255; ++var5) {
                    if (this.hs_link[var5] == -1) {
                        double[] var10000;
                        if (this.hs_up[var5] != -200.0D && this.hs_vp[var5] != -200.0D) {
                            this.SetPAlpha((int) this.hs_xp[var5], (int) this.hs_yp[var5], (int) this.hs_up[var5], (int) this.hs_vp[var5], var5, var1);
                            if (this.hs_up[var5] >= this.hs_xp[var5]) {
                                var10000 = this.hs_xp;
                                var10000[var5] += (this.hs_up[var5] - this.hs_xp[var5]) / 2.0D;
                                this.hs_up[var5] -= this.hs_xp[var5];
                            } else {
                                var10000 = this.hs_xp;
                                var10000[var5] += (this.hs_up[var5] + (double) var3 - this.hs_xp[var5]) / 2.0D;
                                this.hs_up[var5] = this.hs_up[var5] + (double) var3 - this.hs_xp[var5];
                            }

                            this.hs_yp[var5] = (this.hs_yp[var5] + this.hs_vp[var5]) / 2.0D;
                            this.hs_vp[var5] = Math.abs(this.hs_yp[var5] - this.hs_vp[var5]);
                        } else if ((this.hs_imode[var5] & 4) > 0 && this.hs_him[var5] != null && this.hs_him[var5] instanceof Image && this.hs_mask[var5] == null) {
                            this.hs_up[var5] = (double) ((Image) this.hs_him[var5]).getWidth((ImageObserver) null);
                            this.hs_vp[var5] = (double) ((Image) this.hs_him[var5]).getHeight((ImageObserver) null);
                            this.SetPAlpha((int) (this.hs_xp[var5] - this.hs_up[var5] / 2.0D), (int) (this.hs_yp[var5] - this.hs_vp[var5] / 2.0D), (int) (this.hs_xp[var5] + this.hs_up[var5] / 2.0D), (int) (this.hs_yp[var5] + this.hs_vp[var5] / 2.0D), var5, var1);
                        } else if (this.hs_mask[var5] != null && (var19 = this.loadImage(this.hs_mask[var5])) != null) {
                            int[] var9 = new int[var19.getWidth((ImageObserver) null) * var19.getHeight((ImageObserver) null)];
                            var4 = new PixelGrabber(var19, 0, 0, var19.getWidth((ImageObserver) null), var19.getHeight((ImageObserver) null), var9, 0, var19.getWidth((ImageObserver) null));

                            try {
                                var4.grabPixels();
                            } catch (InterruptedException var18) {
                                continue;
                            }

                            var11 = (int) this.hs_yp[var5];
                            var13 = (var5 << 24) + 16777215;
                            var14 = 0;

                            for (int var7 = 0; var7 < var19.getHeight((ImageObserver) null) && var11 < var2; ++var11) {
                                int var8 = var7 * var19.getWidth((ImageObserver) null);
                                int var6 = 0;

                                for (var12 = (int) this.hs_xp[var5]; var6 < var19.getWidth((ImageObserver) null) && var12 < var3; ++var12) {
                                    if ((var9[var8 + var6] & 16777215) == 16777215) {
                                        var1[var11][var12] &= var13;
                                        ++var14;
                                    }

                                    ++var6;
                                }

                                ++var7;
                            }

                            var10000 = this.hs_yp;
                            var10000[var5] += (double) (var19.getHeight((ImageObserver) null) >> 1);
                            var10000 = this.hs_xp;
                            var10000[var5] += (double) (var19.getWidth((ImageObserver) null) >> 1);
                            this.hs_up[var5] = (double) var19.getWidth((ImageObserver) null);
                            this.hs_vp[var5] = (double) var19.getHeight((ImageObserver) null);
                        }
                    }
                }
            }

            for (var5 = 0; var5 < this.numhs; ++var5) {
                if (this.hs_link[var5] != -1) {
                    this.hs_xp[var5] = this.hs_xp[this.hs_link[var5]];
                    this.hs_yp[var5] = this.hs_yp[this.hs_link[var5]];
                    this.hs_up[var5] = this.hs_up[this.hs_link[var5]];
                    this.hs_vp[var5] = this.hs_vp[this.hs_link[var5]];
                }
            }

            for (var5 = 0; var5 < this.numhs; ++var5) {
                if ((this.hs_imode[var5] & 4) > 0 && this.hs_him[var5] != null && this.hs_him[var5] instanceof Image) {
                    var11 = (var19 = (Image) this.hs_him[var5]).getWidth((ImageObserver) null);
                    var12 = var19.getHeight((ImageObserver) null);
                    var13 = (int) this.hs_xp[var5] - (var11 >> 1);
                    var14 = (int) this.hs_yp[var5] - (var12 >> 1);
                    if (var13 >= 0 && var14 >= 0 && var11 + var13 <= var3 && var12 + var14 <= var2) {
                        int[] var15 = new int[var11 * var12 + var11 * var12];
                        var4 = new PixelGrabber(var19, 0, 0, var11, var12, var15, 0, var11);

                        try {
                            var4.grabPixels();
                        } catch (InterruptedException var17) {
                            continue;
                        }

                        this.im_extractRect(var1, var13, var14, var15, var11, 0, var12, var11, var12);
                        this.hs_him[var5] = var15;
                        this.hs_up[var5] = (double) var11;
                        this.hs_vp[var5] = (double) var12;
                    } else {
                        System.out.println("Image for Hotspot No " + var5 + " outside main panorama");
                    }
                }
            }
        }

    }

    boolean hs_drawWarpedImages(int[][] var1, int var2, boolean var3) {
        boolean var9 = false;
        if (var1 == null) {
            return false;
        } else {
            for (int var4 = 0; var4 < this.numhs; ++var4) {
                if ((this.hs_imode[var4] & 4) > 0 && this.hs_him[var4] != null && this.hs_him[var4] instanceof int[]) {
                    int var5 = (int) this.hs_up[var4];
                    int var6 = (int) this.hs_vp[var4];
                    int var7 = (int) this.hs_xp[var4] - (var5 >> 1);
                    int var8 = (int) this.hs_yp[var4] - (var6 >> 1);
                    int[] var10000;
                    if (var3 || (this.hs_imode[var4] & 2) > 0 || var4 == var2 && (this.hs_imode[var4] & 1) > 0 || var2 >= 0 && this.hs_link[var4] == var2 && (this.hs_imode[var4] & 1) > 0) {
                        if ((this.hs_imode[var4] & 8) == 0) {
                            this.im_insertRect(var1, var7, var8, (int[]) this.hs_him[var4], var5, 0, 0, var5, var6);
                            var10000 = this.hs_imode;
                            var10000[var4] |= 8;
                            var9 = true;
                        }
                    } else if ((this.hs_imode[var4] & 8) > 0) {
                        this.im_insertRect(var1, var7, var8, (int[]) this.hs_him[var4], var5, 0, var6, var5, var6);
                        var10000 = this.hs_imode;
                        var10000[var4] &= -9;
                        var9 = true;
                    }
                }
            }

            return var9;
        }
    }

    void hs_rel2abs(int var1, int var2) {
        for (int var3 = 0; var3 < this.numhs; ++var3) {
            if (this.hs_xp[var3] < 0.0D) {
                this.hs_xp[var3] = -this.hs_xp[var3] * (double) var1 / 100.0D;
                if (this.hs_xp[var3] >= (double) var1) {
                    this.hs_xp[var3] = (double) (var1 - 1);
                }
            }

            if (this.hs_yp[var3] < 0.0D) {
                this.hs_yp[var3] = -this.hs_yp[var3] * (double) var2 / 100.0D;
                if (this.hs_yp[var3] >= (double) var2) {
                    this.hs_yp[var3] = (double) (var2 - 1);
                }
            }

            if (this.hs_up[var3] < 0.0D && this.hs_up[var3] != -200.0D) {
                this.hs_up[var3] = -this.hs_up[var3] * (double) var1 / 100.0D;
                if (this.hs_up[var3] >= (double) var1) {
                    this.hs_up[var3] = (double) (var1 - 1);
                }
            }

            if (this.hs_vp[var3] < 0.0D && this.hs_vp[var3] != -200.0D) {
                this.hs_vp[var3] = -this.hs_vp[var3] * (double) var2 / 100.0D;
                if (this.hs_vp[var3] >= (double) var2) {
                    this.hs_vp[var3] = (double) (var2 - 1);
                }
            }
        }

    }

    void hs_draw(Graphics var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
        for (int var8 = 0; var8 < this.numhs; ++var8) {
            if (this.hs_visible[var8] && (var7 || (this.hs_imode[var8] & 2) > 0 || var8 == var6 && (this.hs_imode[var8] & 1) > 0 || var6 >= 0 && this.hs_link[var8] == var6 && (this.hs_imode[var8] & 1) > 0)) {
                if (this.hs_him[var8] == null) {
                    if (this.hs_hc[var8] == null) {
                        var1.setColor(Color.red);
                    } else {
                        var1.setColor(this.hs_hc[var8]);
                    }

                    var1.drawOval(this.hs_xv[var8] - 10 + var2, this.hs_yv[var8] - 10 + var3, 20, 20);
                    var1.fillOval(this.hs_xv[var8] - 5 + var2, this.hs_yv[var8] - 5 + var3, 10, 10);
                } else if (this.hs_him[var8] instanceof Image) {
                    Image var9 = (Image) this.hs_him[var8];
                    var1.drawImage(var9, this.hs_xv[var8] - (var9.getWidth((ImageObserver) null) >> 1) + var2, this.hs_yv[var8] - (var9.getHeight((ImageObserver) null) >> 1) + var3, this);
                } else if ((this.hs_imode[var8] & 16) > 0 && this.hs_him[var8] instanceof String) {
                    String var15 = (String) this.hs_him[var8];
                    Dimension var10 = this.string_textWindowSize(var1, var15);
                    if (this.hs_xv[var8] >= 0 && this.hs_xv[var8] < var4 && this.hs_yv[var8] >= 0 && this.hs_yv[var8] < var5) {
                        int var11 = 0;
                        int var12 = 0;
                        byte var13 = 0;
                        if (this.hs_xv[var8] + var10.width < var4) {
                            if (this.hs_yv[var8] - var10.height > 0) {
                                var11 = this.hs_xv[var8];
                                var12 = this.hs_yv[var8] - var10.height;
                                var13 = 1;
                            } else if (this.hs_yv[var8] + var10.height < var4) {
                                var11 = this.hs_xv[var8];
                                var12 = this.hs_yv[var8];
                                var13 = 2;
                            }
                        } else if (this.hs_xv[var8] - var10.width >= 0) {
                            if (this.hs_yv[var8] - var10.height > 0) {
                                var11 = this.hs_xv[var8] - var10.width;
                                var12 = this.hs_yv[var8] - var10.height;
                                var13 = 3;
                            } else if (this.hs_yv[var8] + var10.height < var4) {
                                var11 = this.hs_xv[var8] - var10.width;
                                var12 = this.hs_yv[var8];
                                var13 = 4;
                            }
                        }

                        if (var13 != 0) {
                            this.string_drawTextWindow(var1, var11 + var2, var12 + var3, var10, this.hs_hc[var8], var15, var13);
                        }
                    }
                }
            }
        }

    }

    final void hs_exec_popup(int var1) {
        for (int var2 = 0; var2 < this.numhs; ++var2) {
            if (this.hs_visible[var2] && this.hs_him[var2] != null && (var2 == var1 || var1 >= 0 && this.hs_link[var2] == var1) && this.hs_him[var2] instanceof String && (this.hs_imode[var2] & 16) == 0) {
                this.JumpToLink((String) this.hs_him[var2], (String) null);
            }
        }

    }

    final void hs_setLinkedHotspots() {
        for (int var1 = 0; var1 < this.numhs; ++var1) {
            for (int var2 = var1 + 1; var2 < this.numhs; ++var2) {
                if (this.hs_xp[var1] == this.hs_xp[var2] && this.hs_yp[var1] == this.hs_yp[var2] && this.hs_link[var1] == -1) {
                    this.hs_link[var2] = var1;
                }
            }
        }

    }

    final void hs_setCoordinates(int var1, int var2, int var3, int var4, double var5, double var7, double var9) {
        int var34 = var3 >> 1;
        int var35 = var4 >> 1;
        double[][] var15 = new double[3][3];
        double var11 = var9 * 2.0D * 3.141592653589793D / 360.0D;
        double var13 = (double) var1 / (2.0D * Math.tan(var11 / 2.0D));
        this.SetMatrix(-var7 * 2.0D * 3.141592653589793D / 360.0D, -var5 * 2.0D * 3.141592653589793D / 360.0D, var15, 0);

        for (int var16 = 0; var16 < this.numhs; ++var16) {
            double var24 = this.hs_xp[var16] - (double) var34;
            double var26 = (double) this.pheight - (this.hs_yp[var16] - (double) var35);
            double var30 = var24 / (double) var34 * 3.141592653589793D;
            double var32 = var26 / (double) var35 * 3.141592653589793D / 2.0D;
            double var22;
            if (Math.abs(var30) > 1.5707963267948966D) {
                var22 = 1.0D;
            } else {
                var22 = -1.0D;
            }

            double var18;
            double var10000 = var18 = var22 * Math.tan(var30);
            double var20 = Math.sqrt(var10000 * var10000 + var22 * var22) * Math.tan(var32);
            var24 = var15[0][0] * var18 + var15[1][0] * var20 + var15[2][0] * var22;
            var26 = var15[0][1] * var18 + var15[1][1] * var20 + var15[2][1] * var22;
            double var28 = var15[0][2] * var18 + var15[1][2] * var20 + var15[2][2] * var22;
            this.hs_xv[var16] = (int) (var24 * var13 / var28 + (double) var1 / 2.0D);
            this.hs_yv[var16] = (int) (var26 * var13 / var28 + (double) var2 / 2.0D);
            int var36 = 12;
            int var37 = 12;
            if (this.hs_him[var16] != null && this.hs_him[var16] instanceof Image) {
                var36 = ((Image) this.hs_him[var16]).getWidth((ImageObserver) null) >> 1;
                var37 = ((Image) this.hs_him[var16]).getHeight((ImageObserver) null) >> 1;
            } else if (this.hs_him[var16] != null && this.hs_him[var16] instanceof String && (this.hs_imode[var16] & 16) > 0) {
                var36 = 100;
                var37 = 100;
            } else if (this.hs_up[var16] != -200.0D && this.hs_vp[var16] != -200.0D) {
                var36 = 100;
                var37 = 100;
            }

            if (this.hs_xv[var16] >= -var36 && this.hs_xv[var16] < this.vwidth + var36 && this.hs_yv[var16] >= -var37 && this.hs_yv[var16] < this.vheight + var37 && var28 < 0.0D) {
                this.hs_visible[var16] = true;
            } else {
                this.hs_visible[var16] = false;
            }
        }

    }

    static Class class$(String var0) {
        try {
            return Class.forName(var0);
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
