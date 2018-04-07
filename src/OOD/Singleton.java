class Singleton{
	private static volatile Singleton instance = null;

	private Singleton(){
	}

	private syncronized Singleton getInstance(){
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}

// volatile: mark a java variable as read/write from the main memory, not from CPU cache.
// This garantee the vairable visibility of changes across threads. For example, in a multi-thread application, 
// where thread operate on non-volatile variables, each therad may copy variables from main memory
// to CPU cache and working on them for performance issue. If more than one CPU, each thread may copy 
// and operate on different CPU cache. 
// Even volatile is not enough, when multiple thread operate on a shared object, use syncrozied to 
// garantee that reading/writeing is atomic. 
