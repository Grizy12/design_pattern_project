package vue;

import model.Dessin;

public interface VueComponent {
    void setDessin(Dessin d);
    int getWidth();
    int getHeight();
}
