function T1_2_4
[t,y] = ode45(@fun,[0,1],[3/2,0]);
plot(t,y(:,1),t,y(:,2))
xlabel('t')
ylabel('x/y')
legend('x(t)', 'y(t)')
function f=fun(t,y)
f=[-3*y(1)-y(2);2*y(1)+3*y(2)+exp(t)];