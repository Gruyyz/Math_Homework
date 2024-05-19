clear;
sym x
y = dsolve('(1+x^2)*D2y=2*x*Dy','y(0)=1','Dy(0)=3','x');
display(y);