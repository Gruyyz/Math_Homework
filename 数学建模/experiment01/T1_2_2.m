function T1_2_2
[x,y] = ode45(@fun,[0,1],[1,3]);
plot(x,y(:,1))
xlabel('x')
ylabel('y')
function f = fun(x,y)
f = [y(2);2*x*y(2)/(1+x^2)];