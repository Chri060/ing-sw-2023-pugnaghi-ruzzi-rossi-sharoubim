package Model;

public class CommonObjTwo extends CommonObjective {


    public CommonObjTwo () {

    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;

        return false;
    }

}