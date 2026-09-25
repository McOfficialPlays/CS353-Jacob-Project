package project.network;

public interface ResultDelimiters {
	String getInputResultDelimiter();
	String getResultDelimiter();
	static ResultDelimiters defaults() {
        return new ResultDelimiters() {
            @Override
            public String getInputResultDelimiter() {
                return ":";
            }

            @Override
            public String getResultDelimiter() {
                return ";";
            }
        };
    }

}
