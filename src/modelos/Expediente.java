package modelos;

public class Expediente {

    private int id;
    private int id_Estudiante;
    private int id_Programa;
    private int number;
    private boolean picturesChecked;
    private boolean cvChecked;
    private boolean negativePhotocopyChecked;
    private boolean gradesChecked;
    private boolean birthCertificateChecked;
    private boolean idPhotocopyChecked;

    public Expediente() {
    }

    public Expediente(int id, int id_Estudiante, int id_Programa, int number, boolean picturesChecked, boolean cvChecked,
                      boolean negativePhotocopyChecked, boolean gradesChecked, boolean birthCertificateChecked,
                      boolean idPhotocopyChecked) {
        this.id = id;
        this.id_Estudiante = id_Estudiante;
        this.id_Programa = id_Programa;
        this.number = number;
        this.picturesChecked = picturesChecked;
        this.cvChecked = cvChecked;
        this.negativePhotocopyChecked = negativePhotocopyChecked;
        this.gradesChecked = gradesChecked;
        this.birthCertificateChecked = birthCertificateChecked;
        this.idPhotocopyChecked = idPhotocopyChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Estudiante() {
        return id_Estudiante;
    }

    public void setId_Estudiante(int id_Estudiante) {
        this.id_Estudiante = id_Estudiante;
    }

    public int getId_Programa() {
        return id_Programa;
    }

    public void setId_Programa(int id_Programa) {
        this.id_Programa = id_Programa;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPicturesChecked() {
        return picturesChecked;
    }

    public void setPicturesChecked(boolean picturesChecked) {
        this.picturesChecked = picturesChecked;
    }

    public boolean isCvChecked() {
        return cvChecked;
    }

    public void setCvChecked(boolean cvChecked) {
        this.cvChecked = cvChecked;
    }

    public boolean isNegativePhotocopyChecked() {
        return negativePhotocopyChecked;
    }

    public void setNegativePhotocopyChecked(boolean negativePhotocopyChecked) {
        this.negativePhotocopyChecked = negativePhotocopyChecked;
    }

    public boolean isGradesChecked() {
        return gradesChecked;
    }

    public void setGradesChecked(boolean gradesChecked) {
        this.gradesChecked = gradesChecked;
    }

    public boolean isBirthCertificateChecked() {
        return birthCertificateChecked;
    }

    public void setBirthCertificateChecked(boolean birthCertificateChecked) {
        this.birthCertificateChecked = birthCertificateChecked;
    }

    public boolean isIdPhotocopyChecked() {
        return idPhotocopyChecked;
    }

    public void setIdPhotocopyChecked(boolean idPhotocopyChecked) {
        this.idPhotocopyChecked = idPhotocopyChecked;
    }

    public boolean isSolvente() {
        return isBirthCertificateChecked() && isCvChecked() && isGradesChecked() && isIdPhotocopyChecked() && isNegativePhotocopyChecked() && isPicturesChecked();
    }
}