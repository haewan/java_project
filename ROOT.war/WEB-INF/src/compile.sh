export JBOSS_HOME=/opt/was/jboss-eap-6.1
export JBOSS_MODULES_BASE=$JBOSS_HOME/modules/system/layers/base

export CLASSPATH=.
export CLASSPATH=$CLASSPATH:$JBOSS_MODULES_BASE/javax/servlet/api/main/jboss-servlet-api_3.0_spec-1.0.2.Final-redhat-1.jar
export CLASSPATH=$CLASSPATH:$JBOSS_MODULES_BASE/org/jboss/log4j/logmanager/main/log4j-jboss-logmanager-1.0.2.Final-redhat-1.jar

CLASSES_HOME=../classes


javac -d $CLASSES_HOME -cp $CLASSPATH $1 
