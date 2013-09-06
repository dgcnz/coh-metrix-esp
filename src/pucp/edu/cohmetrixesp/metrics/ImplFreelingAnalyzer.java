package pucp.edu.cohmetrixesp.metrics;

import edu.upc.freeling.Maco;
import edu.upc.freeling.MacoOptions;
import edu.upc.freeling.Splitter;
import edu.upc.freeling.Tokenizer;
import edu.upc.freeling.Util;

public class ImplFreelingAnalyzer implements IFreelingAnalyzer {
	private static final String FREELINGDIR = "/usr/local";
	private static final String DATA = FREELINGDIR + "/share/freeling/";
	private static final String LANG = "es";
	Maco mf = null;
	Tokenizer tk = null;
	Splitter sp = null;
	static ImplFreelingAnalyzer instance = null;
	public static IFreelingAnalyzer getInstance() {
		if (instance == null) return instance = new ImplFreelingAnalyzer();
		return instance;
	}
	ImplFreelingAnalyzer() {
		System.loadLibrary("freeling_javaAPI");
		Util.initLocale("default");
		MacoOptions op = new MacoOptions(LANG);
		op.setActiveModules(false, true, true, true, true, true, true, true,
				true, true);
		
		op.setDataFiles("", DATA + LANG + "/locucions.dat", DATA + LANG
				+ "/quantities.dat", DATA + LANG + "/afixos.dat", DATA + LANG
				+ "/probabilitats.dat", DATA + LANG + "/dicc.src", DATA + LANG
				+ "/np.dat", DATA + "common/punct.dat");
		// Create analyzers.
		tk = new Tokenizer(DATA + LANG + "/tokenizer.dat");
		sp = new Splitter(DATA + LANG + "/splitter.dat");
		mf = new Maco(op);		
	}
	@Override
	public Maco getMorfological() {
		return mf;
	}

	@Override
	public Tokenizer getTokenizer() {
		return tk;
	}

	@Override
	public Splitter getSplitter() {
		return sp;
	}
	

}
