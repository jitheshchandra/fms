package com.ssrv.fms.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-fms-config.xml" })
// @DatabaseSetup(value = "dataset.xml", type = DatabaseOperation.INSERT)
public abstract class BaseFMSTester {

}