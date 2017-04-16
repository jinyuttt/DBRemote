package LogInfo;

/**
 *  日志类别
 *  本程序的日志不是一般日志内容
 *  一般用的类别：Nomral 一般显示信息， Error 运行错误信息
 *   Test  用于测试信息 建议由全局变量控制
 *   Record 需要记录的信息
 * @author jinyu
 *
 */
public enum LogType {
  Nomral,//一般信息
  Error,//运行错误信息
  Debug,//调试信息，主要是兼容已有信息
  Test,//用于测试信息，打上的测试，建议由全局变量控制
  Warn,//警告信息，本程序不建议使用，不符日志内容
  Record //需要记录的信息
}
