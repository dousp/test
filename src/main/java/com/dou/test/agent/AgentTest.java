package com.dou.test.agent;

/**
 * 代理参看： https://github.com/dousp/cost-time-agent
 * -javaagent:/Users/dou/work/cost-time-agent/build/libs/cost-time-agent-1.0-SNAPSHOT-all.jar
 */
public class AgentTest {

    private void fun1() throws Exception {
        System.out.println("this is fun 1.");
        Thread.sleep(500);
    }

    private void fun2() throws Exception {
        System.out.println("this is fun 2.");
        Thread.sleep(500);
    }

    public static void main(String[] args) throws Exception {
        AgentTest test = new AgentTest();
        test.fun1();
        test.fun2();

    }
}
