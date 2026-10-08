import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;

public class HelloMIDlet extends MIDlet {

    public void startApp() {
        Form form = new Form("Hello");
        form.append("I'm feeling cuddly!");
        Display.getDisplay(this).setCurrent(form);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
