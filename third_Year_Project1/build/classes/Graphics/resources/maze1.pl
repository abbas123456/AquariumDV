% 2222222222
% 3000000002
% 2222222202
% 2000000202
% 2022220202
% 2021000202
% 2022222202
% 2000000002
% 2222222222
%-
path(N,[finish]):-member(finish,N).
path(N,[H2,H|M2]):-setof(X,Y^(member(Y,N),connection(Y,X)),N2),path(N2,[H|M2]),member(H2,N),connection(H2,H).

connection(A,B):- (pair(A,B) ; pair(B,A)).
pair(begin, point(0, 1)).
pair(point(0, 1), point(1, 1)).
pair(point(1, 1), point(2, 1)).
pair(point(2, 1), point(3, 1)).
pair(point(3, 1), point(4, 1)).
pair(point(4, 1), point(5, 1)).
pair(point(5, 1), point(6, 1)).
pair(point(6, 1), point(7, 1)).
pair(point(7, 1), point(8, 1)).
pair(point(8, 1), point(8, 2)).
pair(point(8, 2), point(8, 3)).
pair(point(1, 3), point(1, 4)).
pair(point(1, 3), point(2, 3)).
pair(point(2, 3), point(3, 3)).
pair(point(3, 3), point(4, 3)).
pair(point(4, 3), point(5, 3)).
pair(point(5, 3), point(6, 3)).
pair(point(6, 3), point(6, 4)).
pair(point(8, 3), point(8, 4)).
pair(point(1, 4), point(1, 5)).
pair(point(6, 4), point(6, 5)).
pair(point(8, 4), point(8, 5)).
pair(point(1, 5), point(1, 6)).
pair(finish, point(3, 5)).
pair(point(4, 5), point(5, 5)).
pair(point(4, 5), point(3, 5)).
pair(point(5, 5), point(6, 5)).
pair(point(8, 5), point(8, 6)).
pair(point(1, 6), point(1, 7)).
pair(point(8, 6), point(8, 7)).
pair(point(1, 7), point(2, 7)).
pair(point(2, 7), point(3, 7)).
pair(point(3, 7), point(4, 7)).
pair(point(4, 7), point(5, 7)).
pair(point(5, 7), point(6, 7)).
pair(point(6, 7), point(7, 7)).
pair(point(7, 7), point(8, 7)).