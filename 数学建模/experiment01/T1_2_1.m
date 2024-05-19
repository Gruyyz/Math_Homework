function T1_2_1
[x,y] = ode45(@fun,[0,1],2);
plot(x,y)
xlabel('x')
ylabel('y')
function f = fun(x,y)
f = 8-3*y;