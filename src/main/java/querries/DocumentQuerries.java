package querries;

public class DocumentQuerries {

    public static String FIND_ALL_DOCUMENTS="FROM Document d";
    public static String FIND_DOCUMENT_BY_ID="FROM Document d WHERE d.id=:document_id";
    public static String FIND_DOCUMENT_BY_NAME="FROM Document d WHERE d.tip=:document_tip";

}
