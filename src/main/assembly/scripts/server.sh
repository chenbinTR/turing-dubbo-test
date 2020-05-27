#!/bin/bash

cd `dirname $0`
BIN_DIR=`pwd`
cd ..
BASE_DIR=`pwd`
CONF_DIR="$BASE_DIR/conf"
LIB_DIR="$BASE_DIR/lib"
LOG_DIR="$BASE_DIR/logs"
LOG_FILE="$LOG_DIR/server.log"
PID_FILE="$LOG_DIR/.run.PID"
SERVER_USER=`whoami`
MAIN_CLASS="com.turing.ask.dubbo.AskClient"

source $BIN_DIR/env.sh

#check server running
function running(){
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        process=`ps aux|grep "$PID" | grep -v grep`;
        if [ "$process" == "" ]; then
            return 1;
        else
            return 0;
        fi
    else
        return 1
    fi
}

#start server
function start_server(){
    if running; then
        echo "server is already running."
        exit 1
    fi
    #create dir
    if [ ! -d $LOG_DIR ]; then
        mkdir -p $LOG_DIR
    fi
    touch $LOG_FILE
    chown -R $SERVER_USER $LOG_DIR

    echo "Starting the server..."
    #echo "$JAVA $JAVA_OPTS $MAIN_CLASS"
    nohup $JAVA $JAVA_OPTS $MAIN_CLASS 2>&1 >> $LOG_FILE &
    echo $! > $PID_FILE
    chmod 755 $PID_FILE

    #check running
}

#stop server
function stop_server(){
    if ! running; then
        echo "Error: Server is not running."
        exit 1
    fi
    PID=$(cat $PID_FILE)
    echo -e "Stopping the server...\c"
    kill $PID > /dev/null 2>&1

    count=0
    while [ $count -lt 1 ]; do
        echo -e ".\c"
        sleep 1
        count=1
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            count=0
            break
        fi
    done
    echo "OK!"
    echo "PID: $PID"
}

#help
function help(){
    echo "Usage: server.sh {start|stop|restart|help}" >&2
    echo "     start:       start server"
    echo "     stop:        stop server"
    echo "     restart:     restart server"
}

command=$1
shift 1
case $command in
    start)
        start_server $@;
        ;;
    stop)
        stop_server $@;
        ;;
    restart)
        $0 stop $@
        $0 start $@
        ;;
    help)
        help;
        ;;
    *)
        help;
        exit 1;
        ;;
esac
