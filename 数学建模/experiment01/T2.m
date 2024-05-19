clear;
syms x
T = dsolve('D2T=alpha*B/(lambda*A)*(T-T3)','x');
display(T);
pretty(T)