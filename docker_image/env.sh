# copied this stuff from dyalog/dyalog /entrypoint
export DYALOG=/opt/mdyalog/18.0/64/unicode/
export LD_LIBRARY_PATH="${DYALOG}:${LD_LIBRARY_PATH}"
export WSPATH=$WSPATH:${DYALOG}/ws
export TERM=dumb
export APL_TEXTINAPLCORE=${APL_TEXTINAPLCORE-1}
export TRACE_ON_ERROR=0
export SESSION_FILE="${SESSION_FILE-$DYALOG/default.dse}"
