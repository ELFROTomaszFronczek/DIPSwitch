package eu.elfro.dipswitch.UTIL;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;

import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidx.documentfile.provider.DocumentFile;


/**
 * Created by BMS on 2015-11-05.
 */
public class PROCKI {

    public static final String DEBUG_TAG = "ELFRO";


    public static Context kontekst;

    // -------------------  SYSTEM PLIKÓW -------------------------
    static String _path = "";
    public static String APP_NAME =  "ELFRO_DIP_SWITCH";


    public static void setValue(String valueName, String value) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString( valueName, value);
            editor.apply();
        } catch (Exception ex) {
        }
    }


    public static void setValue(String valueName, int value) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME,  0 );
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt( valueName, value);
            editor.apply();
        } catch (Exception ex) {
        }
    }


    public static void setValue(String valueName, float value) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putFloat( valueName, value);
            editor.apply();
        } catch (Exception ex) {
            LogException(ex);
        }
    }

    public static void setValue(String valueName, double value) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong( valueName, Double.doubleToRawLongBits(value));
            editor.apply();
        } catch (Exception ex) {
            LogException(ex);
        }
    }




    public static void setValue(String valueName, boolean value) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean( valueName, value);
            editor.apply();
        } catch (Exception ex) {
        }
    }


    public static void setValue(String valueName, long value) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong( valueName, value);
            editor.apply();
        } catch (Exception ex) {
        }
    }


    public static String getValueFromCFG(String valueName) {
        SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
        return settings.getString( valueName, "");

    }


    public static String getStringValue(String valueName) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getString( valueName, "");
        } catch (Exception ex) {
        }
        return "";
    }

    public static String getStringValue(String valueName, String def) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getString( valueName, def);
        } catch (Exception ex) {
        }
        return "";
    }

    public static long getLongValue(String valueName) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getLong( valueName, -1);
        } catch (Exception ex) {
        }
        return -1;
    }

    public static long getLongValue(String valueName, long defaultVal) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getLong( valueName, defaultVal);
        } catch (Exception ex) {
        }
        return defaultVal;
    }


    public static int getIntValue(String valueName) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getInt( valueName, -1);
        } catch (Exception ex) {
        }
        return -1;
    }

    public static int getIntValue(String valueName, int defaultVal) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getInt( valueName, defaultVal);
        } catch (Exception ex) {
        }
        return defaultVal;
    }





    public static boolean getBoolValue(String valueName) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getBoolean( valueName, false);
        } catch (Exception ex) {
        }
        return false;
    }

    public static boolean getBoolValue(String valueName, boolean defaultVal) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getBoolean( valueName, defaultVal);
            
        } catch (Exception ex) {
        }
        return defaultVal;
    }


    public static float getFloatValue(String valueName, float defaultVal) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return settings.getFloat( valueName, defaultVal);

        } catch (Exception ex) {
            LogException(ex);
        }
        return defaultVal;
    }


    public static double getDoubleValue(String valueName, double defaultVal) {
        try {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 0);
            return Double.longBitsToDouble(settings.getLong( valueName, Double.doubleToRawLongBits(defaultVal)));

        } catch (Exception ex) {
            LogException(ex);
        }
        return defaultVal;
    }

   /* public static void setPath(String value) {
        SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 3);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("myPath", value);
        editor.apply();
        _path = value;
    }*/

    public static void SetContext(Context _kontekst) {
        kontekst = _kontekst;
    }


    public static String getStorage() {
        return getStringValue("myStorage");
    }

    public static String getRouteName() {
        return getStringValue("myRoute");
    }

    public static void setStorage(String storageName) {
        setValue("myStorage", storageName);
    }

    public static void setRoute(String routeName) {
        setValue("myRoute", routeName);
    }


    public static String getOnlyPath() {
        /*if (PROCKI.stringIsNullOrEmpty(_path)) {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 3);
            _path = settings.getString("myPath", "");//GetDefInternalStorage());
        }
        return _path;*/
        return PROCKI.addFolderSeparator(getStorage()) + APP_NAME;

    }

    public static String getPath() {
        /*if (PROCKI.stringIsNullOrEmpty(_path)) {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 3);
            _path = settings.getString("myPath", "");//GetDefInternalStorage());
        }
        return _path;*/
        String Path = PROCKI.addFolderSeparator(getStorage()) + APP_NAME;
        String Route = getRouteName();
        if (PROCKI.stringIsNullOrEmpty(Route)) return Path;
        else
            return PROCKI.addFolderSeparator(Path) + Route;
    }

    public static String twoDigit(int value) {

        if (value > -1 && value < 10) return "0" + value;
        return String.valueOf(value);
    }

    public static String getAndCreatePath() {
        /*if (PROCKI.stringIsNullOrEmpty(_path)) {
            SharedPreferences settings = kontekst.getSharedPreferences(APP_NAME, 3);
            _path = settings.getString("myPath", "");//GetDefInternalStorage());
        }
        return _path;*/
        String storage=getStorage();
        if (!isFileExists(storage)) createDir(storage);
        String Path = PROCKI.addFolderSeparator(storage) + APP_NAME;
        if (!isFileExists(Path)) createDir(Path);
        String Route = getRouteName();
        if (PROCKI.stringIsNullOrEmpty(Route)) return Path;
        else {
            Path =PROCKI.addFolderSeparator(Path) + Route;
            if (!isFileExists(Path)) createDir(Path);
            return Path;
        }
    }


    public static String addZero(int d) {
        if (d < 10) return "0" + d;
        return Integer.toString(d);
    }


    public static String getDirectoryFromPath(String path) {
        try {
            //    File f = new File(Path);
            //   return f.getName();
            if (path != null && path.indexOf("/") > -1) {

                return path.substring(0, path.lastIndexOf("/"));

            }
            return path;

        } catch (Exception ex) {
        }
        return path;
    }

    public static boolean createDir(String dir) {
        try {
            if (dir.indexOf("://") < 0) {
                File directory = new File(removeFolderSeparator(dir));

                if (!directory.exists()) directory.mkdir();
                MediaScannerConnection.scanFile(kontekst, new String[]{directory.getAbsolutePath()}, null, null);
                directory.setReadable(true, false);
                directory.setWritable(true, false);

                return directory.exists();
            } else {


                String root = findRoot(dir);
                Uri uri = Uri.parse(root);
                DocumentFile df = fromString(root);
                //   DocumentFile df= DocumentFile.fromTreeUri(kontekst,uri);
                //if (!df.equals(df2))
                // {

                String[] parts = dir.replace(root + "/", "").split("/");
                DocumentFile nextDocument = null;
                if (parts == null) return true;
                for (int i = 0; i < parts.length; i++) {
                    if (stringIsNullOrEmpty(parts[i])) continue;
                    nextDocument = df.findFile(parts[i]);
                    //  PROCKI.LogLog("DIR part: " + parts[i]);

                    if (nextDocument == null)
                        nextDocument = df.createDirectory(parts[i]);
                    if (nextDocument != null) df = nextDocument;

                }
                // } //else return true;

                return nextDocument != null;
            }

        } catch (Exception ex) {
            PROCKI.LogLogException(ex);
            return false;
        }

    }

    public static DocumentFile fromString(String dir) {
        Uri uri = Uri.parse(dir);
        DocumentFile df = DocumentFile.fromTreeUri(kontekst, uri);
        String[] el = dir.split("/");
        DocumentFile prev;
        int i = el.length - 1;
        for (i = el.length - 1; i > -1; i--) {
            prev = df.findFile(el[i]);
            if (prev != null) {
                df = prev;
                break;
            }
        }
        if (i < 0) i = 0;
        if (i < el.length)
            for (int j = i; j < el.length; j++) {
                prev = df.findFile(el[j]);
                if (prev != null) {
                    df = prev;

                }
            }
        return df;


    }


    public static DocumentFile fromString2(String dir) {
        Uri uri = Uri.parse(dir);
        DocumentFile df = DocumentFile.fromTreeUri(kontekst, uri);
        String[] el = dir.split("/");
        DocumentFile prev;

        for (int j = 0; j < el.length; j++) {
            prev = df.findFile(el[j]);
            if (prev != null) {
                return prev;

            }
        }
        return df;


    }


    public static boolean deleteFile(String fileName) {
        try {
            if (fileName.indexOf("://") > -1) return true; //  zabezpieczenie przed kasowaniem na karcie SD na razie !!!
            unMakeFilePCReadable(fileName);
            File f = new File(removeFolderSeparator(fileName));
            if (!f.exists()) return true;
            f.setReadable(true, false);
            f.setWritable(true, false);
            return f.delete();

        } catch (Exception ex) {
            return false;
        }

    }

    public static String[] getFiles(String path, final String ext) {
        try {
            if (path.indexOf("://") < 0) {
                File dir = new File(path);
                File[] files = dir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(ext.toLowerCase());
                    }
                });
                String[] s = new String[files.length];
                for (int i = 0; i < s.length; i++) {
                    s[i] = files[i].getCanonicalPath();
                }
                return s;

            }
            else
            {
                DocumentFile df= fromString(path);
                DocumentFile[] d =df.listFiles();
                int ile=0;
                for(DocumentFile d1:d) if (d1.getUri().toString().endsWith(ext)) ile++;
                String[] s = new String[ile];
                ile=0;
                for(DocumentFile d1:d) if (d1.getUri().toString().endsWith(ext)){ s[ile]=d1.getUri().toString(); ile++;}
                return s;
            }

        } catch (Exception ex) {
        }
        return null;

    }

    public static String getTimedFileName()
    {
        return PROCKI.getDateTime(" ").replace(":","_")+"";
    }

    public static String findRoot(String fileName) {
        Uri uri = Uri.parse(fileName);
        DocumentFile df = DocumentFile.fromTreeUri(kontekst, uri);
        String[] path = df.getUri().getPath().split(":/");
        String root = df.getUri().toString();
        if (path != null && root.indexOf(path[0]) > -1) {
            root = root.substring(0, root.indexOf(path[0]) + path[0].length());
            root += getDirectoryFromPath(fileName.replace(root, ""));
        }
        // PROCKI.LogLog("FindRoot: " + root);
        return root;
    }


    public static boolean isFileExists(String fileName) {
        try {
            if (fileName.indexOf("://") < 0) {
                String Path = removeFolderSeparator(fileName);
                File nF = new File(Path);
                return nF.exists();
            } else {

                String root = findRoot(fileName);
                Uri uri = Uri.parse(root);
                DocumentFile df = fromString(root);


                // start with root of SD card and then parse through document tree.
                //  DocumentFile df = DocumentFile.fromTreeUri(kontekst, uri);
/*                PROCKI.LogLog("fileName "+fileName);
                PROCKI.LogLog("URI toString "+uri.toString());
                PROCKI.LogLog("URI getPath "+uri.getPath());
                PROCKI.LogLog("URI getFragment "+uri.getFragment());
                PROCKI.LogLog("URI getLastPathSegment "+uri.getLastPathSegment());

                PROCKI.LogLog("df.getUri toString "+df.getUri().toString());
                PROCKI.LogLog("df.getUri getPath " + df.getUri().getPath());
                PROCKI.LogLog("df.getUri getFragment " + df.getUri().getFragment());
                PROCKI.LogLog("df.getUri getLastPathSegment " + df.getUri().getLastPathSegment());
                */


                String[] parts = fileName.replace(root + "/", "").split("/");
                DocumentFile nextDocument = null;
                for (int i = 0; i < parts.length; i++) {
                    nextDocument = df.findFile(parts[i]);
                    //    PROCKI.LogLog("find part: " + parts[i]);
                    //  if (nextDocument == null) {
                    //  if ((i < parts.length - 1)) {
                    //    nextDocument = df.createDirectory(parts[i]);
                    //  break;
                    //}

                    // }
                    if (nextDocument != null) df = nextDocument;

                }


                //    if (nextDocument != null) PROCKI.LogLog("Exists " + nextDocument.getUri());
                //  else PROCKI.LogLog("not_Exists");
                if (nextDocument != null) return nextDocument.exists();
                else return false;

            }
        } catch (Exception ex) {
            PROCKI.LogLogException(ex);
            return false;
        }
    }


    public static boolean makeFilePCReadable(String fileName) {
        try {
            String fN = removeFolderSeparator(fileName);
            File file = new File(fN);
            MediaScannerConnection.scanFile(kontekst, new String[]{file.getAbsolutePath()}, null, null);
            file.setReadable(true, false);
            file.setWritable(true, false);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean unMakeFilePCReadable(String fileName) {
        try {
            String fN = removeFolderSeparator(fileName);
            File file = new File(fN);
            MediaScannerConnection.scanFile(kontekst, new String[]{file.getAbsolutePath(), ""}, null, null);


            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public static String getFileNameWithoutExtension(String filenameWithExtnsion) {
        try {
            if (stringIsNullOrEmpty(filenameWithExtnsion)) return filenameWithExtnsion;
            filenameWithExtnsion=PROCKI.getFileNameFromPath(filenameWithExtnsion);
            if (stringIsNullOrEmpty(filenameWithExtnsion)) return filenameWithExtnsion;
            int pos = filenameWithExtnsion.lastIndexOf(".");
            int sep = filenameWithExtnsion.lastIndexOf("/");
            if (pos == -1 || (sep>0&& pos<sep)) return filenameWithExtnsion;
            return filenameWithExtnsion.substring(0, pos);
        } catch (Exception ex) {
        }
        return "";
    }

    public static String getFileNameExtension(String filenameExtnsion) {
        try {

            if (stringIsNullOrEmpty(filenameExtnsion)) return filenameExtnsion;
            int pos = filenameExtnsion.lastIndexOf(".");
            if (pos == -1) return "";
            int sep = filenameExtnsion.lastIndexOf("/");
            if (sep>0&& pos<sep) return "";
            return filenameExtnsion.substring(pos);
        } catch (Exception ex) {
        }
        return "";
    }

    public static String getFileNameFromPath(String Path) {
        try {
            //    File f = new File(Path);
            //   return f.getName();
            String[] s = Path.split("/");
            if (s != null && s.length > 0) return s[s.length - 1];
            else return Path;

        } catch (Exception ex) {
        }
        return Path;

    }


    static char[] InvalidChars = {'\\', '/', '?', ':', '*', '"', '>', '<', '|', '[', ']', '\'', '\n', '\r', '\t'};

    public static boolean isValidChars(String text) {
        if (PROCKI.stringIsNullOrEmpty(text)) return false;

        for (int i = 0; i < InvalidChars.length; i++)
            if (text.indexOf(InvalidChars[i]) >= 0) return false;
        return true;
    }

    public static boolean isValidChars(String text, char[] invalidChars) {
        if (PROCKI.stringIsNullOrEmpty(text)) return false;

        for (int i = 0; i < invalidChars.length; i++)
            if (text.indexOf(invalidChars[i]) >= 0) return false;
        return true;
    }

    public static String removeBadChars(String text) {
        if (PROCKI.stringIsNullOrEmpty(text)) return text;
        for (int i = 0; i < InvalidChars.length; i++) {
            text = text.replace(Character.toString(InvalidChars[i]), "");
        }
        return text;
    }


    public static String removeBadChars(String text, char[] invalidChars) {
        if (PROCKI.stringIsNullOrEmpty(text)) return text;
        for (int i = 0; i < invalidChars.length; i++) {
            text = text.replace(Character.toString(invalidChars[i]), "");
        }
        return text;
    }

    public static String removeSpaces(String text) {
        if (PROCKI.stringIsNullOrEmpty(text)) return text;
        return text.replace(" ", "");
    }


    public static boolean stringIsNullOrEmptyOrBAD(String text) {
        return !isValidChars(text);
    }


    //------------------------- STRINGI -------------------------
    public static boolean stringIsNullOrEmpty(String str) {

        if (str == null) return true;
        return str.isEmpty();
    }

    public static String addFolderSeparator(String text) {
        if (text.endsWith(File.separator)) return text;
        return text + File.separator;
    }

    public static String removeFolderSeparator(String text) {
        if (text.endsWith("/") && text.length() > 0)
            return text.substring(0, text.length() - 1);
        return text;
    }


    public static String osVersion() {
        return System.getProperty("os.version");
    }

    public static String phoneModel() {
        return android.os.Build.MODEL;
    }

    public static String androidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    static char dSep = '0';

    public static char DecimalSeparator() {
        if (dSep == '0') {
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dSep = dfs.getDecimalSeparator();
        }
        return dSep;
    }

    /// <summary>
    /// Zamienia "przecinek" w liczbach na kropkę
    /// </summary>
    /// <param name="value">tekst-liczba</param>
    /// <returns>tekst-liczba z . po przecinku</returns>
    public static String ConvDecimalSepToDot(String value) {
        char DS = DecimalSeparator();
        if (DS != '.')
            if (value.indexOf(DS) >= 0) value = value.replace(DS, '.');
        return value;
    }

    /// <summary>
    /// Zamienia kropkę . na przecinek w liczbach
    /// </summary>
    /// <param name="value">tekst-liczba z . po przecinku</param>
    /// <returns>tekst-liczba z przecinkiem</returns>
    public static String ConvDotToDecimalSep(String value) {
        char DS = DecimalSeparator();
        if (DS != '.')
            if (value.indexOf(".") >= 0) value = value.replace('.', DS);
        if (DS != ',')
            if (value.indexOf(",") >= 0) value = value.replace(',', DS);

        return value;
    }


    public static String doubleToString_2DOT(double d) {
       // NumberFormat formatter = new DecimalFormat("%.2f");
        return PROCKI.ConvDotToDecimalSep(String.format("%.2f",d));
    }

    public static String doubleToString_4DOT(double d) {
        // NumberFormat formatter = new DecimalFormat("%.2f");
        return PROCKI.ConvDotToDecimalSep(String.format("%.4f",d));
    }


    public static String doubleToString_2DOTDOT(double d) {
       // NumberFormat formatter = new DecimalFormat("%.2f");
        return PROCKI.ConvDecimalSepToDot(String.format("%.2f",d));
    }

    public static String doubleToString_4DOTDOT(double d) {
        // NumberFormat formatter = new DecimalFormat("%.2f");
        return PROCKI.ConvDecimalSepToDot(String.format("%.4f",d));
    }

    public static String doubleToString_6DOTDOT(double d) {
        // NumberFormat formatter = new DecimalFormat("%.2f");
        return PROCKI.ConvDecimalSepToDot(String.format("%.6f",d));
    }

    public static String doubleToString_1DOT(double d) {
      //  NumberFormat formatter = new DecimalFormat("%.1f");
        return PROCKI.ConvDotToDecimalSep(String.format("%.1f",d));
    }

    public static String doubleToString_1DOTDOT(double d) {
       // NumberFormat formatter = new DecimalFormat("%.1f");
        return PROCKI.ConvDecimalSepToDot(String.format("%.1f",d));
    }

    final protected static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String byteToHex(byte b) {

        /*String s = Integer.toHexString((0xFF & b));
        if (s.length() > 1) return s;
        else return "0" + s;*/

        return hexArray[0x0F & (b >> 4)] + "" + hexArray[0x0F & b];

    }

    // final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        try {
            char[] hexChars = new char[(bytes.length * 3) + ((bytes.length / 32) * 3)];
            int loc = 0;
            for (int j = 0; j < bytes.length; j++) {
                int v = bytes[j] & 0xFF;

                hexChars[loc++] = hexArray[v >>> 4];
                hexChars[loc++] = hexArray[v & 0x0F];
                if (j != 0 && j % 16 == 15) {
                    hexChars[loc++] = '\n';
                    if (j != 0 && j % 32 == 31) ;
                    else {
                        hexChars[loc++] = ' ';
                        hexChars[loc++] = ' ';
                        hexChars[loc++] = ' ';
                    }
                } else hexChars[loc++] = ' ';

            }
            return new String(hexChars);
        } catch (Exception ex) {
        }
        return "Błąd";
    }


    public static String removePolishChars(String s) {
        return removePolishSmallChars(removePolishBIGChars(s));

    }


    public static String removePolishBIGChars(String s) {
        return s.
                replace('Ę', 'E').
                replace('Ó', 'O').
                replace('Ą', 'A').
                replace('Ś', 'S').
                replace('Ł', 'L').
                replace('Ż', 'Z').
                replace('Ź', 'Z').
                replace('Ć', 'C').
                replace('Ń', 'N');
    }

    public static String removePolishSmallChars(String s) {
        return s.
                replace('ę', 'e').
                replace('ó', 'o').
                replace('ą', 'a').
                replace('ś', 's').
                replace('ł', 'ł').
                replace('ż', 'z').
                replace('ź', 'z').
                replace('ć', 'c').
                replace('ń', 'n');
    }


    ///--------------------------------------LICZBY

    public static int tryParseInt(String value, int defValue) {
        if (stringIsNullOrEmpty(value)) return defValue;
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
        }
        return defValue;
    }

    public static long tryParseLong(String value, long defValue) {
        if (stringIsNullOrEmpty(value)) return defValue;
        try {
            return Long.parseLong(value);
        } catch (Exception ex) {
        }

        return defValue;

    }

    public static double tryParseDouble(String value, double defValue) {
        if (stringIsNullOrEmpty(value)) return defValue;
        try {
            return Double.parseDouble(value);
        } catch (Exception ex) {
        }

        return defValue;

    }


    //  static String lastErr = "";
    //  static long lastErrTime = 0;
    static int errCount;

    public static String[] lastErrors = new String[]{"", "", "", ""};
    public static long[] lastErrorsTime = new long[]{0, 0, 0, 0};

    static boolean isError(String err) {
        try {
            int jest = -1;
            long jestTime = -1;
            int lng = lastErrors.length;

            for (int i = 0; i < lng; i++)
                if (lastErrors[i].equals(err)) {
                    jest = i;
                    jestTime = lastErrorsTime[i];
                }

            long time = System.currentTimeMillis() / 1000;
            if (jest > -1) {
                if (jest < lng - 1)  //przesun do gory
                {
                    for (int i = jest; i < lng - 1; i++) {
                        lastErrors[i] = lastErrors[i + 1];
                        lastErrorsTime[i] = lastErrorsTime[i + 1];
                    }
                }

                lastErrors[3] = err;
                lastErrorsTime[3] = time;
                return time - jestTime > 120; // jak stary error to pozwol go dodac
            } else {
                for (int i = 0; i < lng - 1; i++) {
                    lastErrors[i] = lastErrors[i + 1];
                    lastErrorsTime[i] = lastErrorsTime[i + 1];
                }
                lastErrors[lng - 1] = err;
                lastErrorsTime[lng - 1] = time;
                return true; // nowy error
            }
        } catch (Exception ex) {
        }
        return true;
    }

    public static void LogException(Exception ex) {
        try {

            StackTraceElement[] st = ex.getStackTrace();
            if (st != null) {
                String shortMsg = ex.getMessage();
                for (int i = 0; i < st.length; i++) {
                    shortMsg += GET_MSG(st[i]);
                }


                Log.e(PROCKI.DEBUG_TAG, shortMsg);
               /*  long time = System.currentTimeMillis() / 1000;
               if (time - lastErrTime > 20) {
                    errCount = 0;
                } else {
                    errCount++;
                    lastErrTime = time;
                }*/

               // if (opcje.RECsystem && isError(shortMsg)) {
                    //if (!shortMsg.equals(lastErr) && errCount < 4) {
                  //  Logger.SaveLine(Logger.ERR, multilineToLine(shortMsg).replace("pl.xtz750(", "").replace(")", ""));
                    //  lastErr = shortMsg;
                    //}
               // }
               // Log.i("ELFRO",ex.toString());
            }

        } catch (Exception ex1) {
            Log.e(PROCKI.DEBUG_TAG, "Unhandled Exception - Exception in LogException :((( T.F.");
        }
    }

    private static String GET_MSG(StackTraceElement st) {
        String s = "";

        try {
            if (st.toString().indexOf("pl.xtz750") > -1)
                s = "\npl.xtz750(" + st.getFileName() + ":" + st.getLineNumber() + ")";
            //s="\n"+st.toString();


        } catch (Exception exe) {
        }
        return s;
    }


    /*public static void LogLogException(Exception ex) {
        try {

            Log.e(PROCKI.DEBUG_TAG, ex.getClass().toString() + "\n" + Log.getStackTraceString(ex));

        } catch (Exception ex1) {
            Log.e(PROCKI.DEBUG_TAG, "Unhandled Exception - Exception in LogException :((( T.F.");
        }
    }*/

    public static void LogLogException(Exception ex) {
        try {

            StackTraceElement[] st = ex.getStackTrace();
            if (st != null) {
                String shortMsg = ex.getMessage();
                for (int i = 0; i < st.length; i++) {
                    shortMsg += GET_MSG(st[i]);
                }
                Log.e(PROCKI.DEBUG_TAG, shortMsg);

            }

        } catch (Exception ex1) {
            Log.e(PROCKI.DEBUG_TAG, "Unhandled Exception - Exception in LogException :((( T.F.");
        }
    }


    public static void Log(String msg) {
        Log.d(PROCKI.DEBUG_TAG, msg);
        // if (opcje.RECsystem)
        // Logger.SaveLine(Logger.LOG, multilineToLine(msg));
    }

    public static void LogLog(String msg) {
        if (!PROCKI.stringIsNullOrEmpty(msg))
            Log.d(PROCKI.DEBUG_TAG, msg);
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = kontekst.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = kontekst.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }


    public static String getDate() {

        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.YEAR) + "-" + addZero(calendar.get(Calendar.MONTH) + 1) + "-" + addZero(calendar.get(Calendar.DAY_OF_MONTH));

    }

    public static String getTime() {

        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return PROCKI.addZero(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + PROCKI.addZero(calendar.get(Calendar.MINUTE)) + ":" + addZero(calendar.get(Calendar.SECOND));

    }

    public static String getTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        String timeZone = new SimpleDateFormat("Z").format(calendar.getTime());
        return timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5);
    }

    public static String getDateTime(String sep) {

        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);


        return calendar.get(Calendar.YEAR) + "-" + addZero(calendar.get(Calendar.MONTH) + 1) + "-" + addZero(calendar.get(Calendar.DAY_OF_MONTH)) + sep + PROCKI.addZero(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + PROCKI.addZero(calendar.get(Calendar.MINUTE)) + ":" + addZero(calendar.get(Calendar.SECOND));

    }


    public static String multilineToLine(String line) {
        return line.replace("\r", "").replace("\n", ";").replace("\t", "\\t");
    }


    public static String getDirectoryName(String currentFile) {
        File file = new File(currentFile);
        file = new File(file.getAbsolutePath());
        return file.getParent();
    }


    public static Date parsedDate;

    public static boolean TryParseDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parsedDate = format.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static String getVersion(Context kon) {
        PackageInfo pInfo = null;
        String version = "";
        try {
            pInfo = kon.getPackageManager().getPackageInfo(kon.getPackageName(), 0);
            version = "ver.:" + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        String ext = PROCKI.getFileNameExtension(kon.getApplicationInfo().packageName);
        String name = PROCKI.getFileNameExtension(PROCKI.getFileNameWithoutExtension(kon.getApplicationInfo().packageName));
        return name + ext + " " + version;
    }

    public static String getLastUriSegment(String separator, String uri) {
        try {
            //    File f = new File(Path);
            //   return f.getName();
            String[] s = uri.split(separator);
            if (s != null && s.length > 0) return s[s.length - 1];
            else return uri;

        } catch (Exception ex) {
        }
        return uri;
    }

    public static String getString(int messageID) {
        return PROCKI.kontekst.getResources().getString(messageID);
    }

    public static String trimEnd(String myString ) {

        for ( int i = myString.length() - 1; i >= 0; --i ) {
            if ( myString.charAt(i) == ' ' ) {
                continue;
            } else {
                myString = myString.substring( 0, ( i + 1 ) );
                break;
            }
        }
        return myString;
    }

    public static String trimBegin(String myString ) {

        for ( int i =0 ; i < myString.length() - 1; i++ ) {
            if ( myString.charAt(i) == ' ' ) {
                continue;
            } else {
                myString = myString.substring( ( i  ));
                break;
            }
        }
        return myString;
    }

    public static String removeBeginAndEndSpaces(String toString) {
        return trimBegin(trimEnd(toString));
    }
}


