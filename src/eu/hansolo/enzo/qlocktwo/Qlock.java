package eu.hansolo.enzo.qlocktwo;

import java.util.List;


/**
 * Created by
 * User: hansolo
 * Date: 27.02.13
 * Time: 15:43
 */
public interface Qlock {
    public String[][] getMatrix();

    public List<QlockWord> getTime(final int MINUTE, final int HOUR);

    public boolean isP1();

    public boolean isP2();

    public boolean isP3();

    public boolean isP4();

    public QlockTwo.Language getLanguage();
}
