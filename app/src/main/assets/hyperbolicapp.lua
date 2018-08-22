-- Sample luaj code to try loading and executing the 'hyperbolic' sample library
--
-- The sample library source is in examples/jse/hyperbolic.java.
-- For this sample to work, that source must be compiled, and the class must
-- be on the class path.
--
-- First load the library via require().  This will call the public constructor
-- for the class named 'hyperbolic' if it exists, and then initialize the
-- library by invoking LuaValue.call('hyperbolic') on that instance
require 'hyperbolic'

LuajLog = luajava.bindClass("com.reizx.luaj.component.LuajLog") --Log打印类

-- Test that the table is in the globals, and the functions exist.
-- LuajLog:d('hyperbolic : '..hyperbolic)
-- LuajLog:d('hyperbolic.sinh : '..hyperbolic.sinh)
-- LuajLog:d('hyperbolic.cosh : '..hyperbolic.cosh)

-- Try exercising the functions.
LuajLog:d('sinh(0.5) : '..hyperbolic.sinh(0.5))
LuajLog:d('cosh(0.5) : '..hyperbolic.cosh(0.5))