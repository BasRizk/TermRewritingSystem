% Original Rules
% and(X, 0) = 0
% and(0, X) = 0
% and(1, X) = X
% and(X, 1) = X
% and(X, X) = X
% and(0,and(X,Y)) = 0
% and(1,and(X,Y)) = and(X,Y)
% 
% Regex :: [a-z]+([(][a-zA-Z]([,][a-zA-Z])*[)])?
% 
% Query Ex.
% ?- eq(T, and(and(1,0), X)).
% eq(T, 0).

:- use_module(library(chr)).
:- chr_constraint eq/2, and/2.

eq(T, and(T1, T2)), eq(T1, _), eq(T2, 0) <=> eq(T, T3), eq(T3,0).
eq(T, and(T1, T2)), eq(T1, 0), eq(T2, _) <=> eq(T, T4), eq(T4, 0).
eq(T, and(T1, T2)), eq(T1, 1), eq(T2, X) <=> eq(T, X).
eq(T, and(T1, T2)), eq(T1, X), eq(T2, 1) <=> eq(T, X).
eq(T, and(T1, T2)), eq(T1, X), eq(T2, X) <=> eq(T, X).
eq(T, and(T1, T2)), eq(T1, 0), eq(T2, and(T3, T4)), eq(T3, _), eq(T4, _) <=> eq(T, T5), eq(T5, 0).
eq(T, and(T1, T2)), eq(T1, 0), eq(T2, and(T3, T4)), eq(T3, X), eq(T4, Y) <=> eq(T, T5), eq(T5, and(T6, T7)), eq(T6,X), eq(T7,Y).