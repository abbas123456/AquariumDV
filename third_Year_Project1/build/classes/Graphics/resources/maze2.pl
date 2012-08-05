% 232222222
% 202000002
% 202222202
% 200000202
% 202222202
% 202100002
% 202222202
% 200000002
% 222222222
%-
path(N,[finish]):-member(finish,N).
path(N,[H2,H|M2]):-setof(X,Y^(member(Y,N),connection(Y,X)),N2),path(N2,[H|M2]),member(H2,N),connection(H2,H).

connection(A,B):- (pair(A,B) ; pair(B,A)).
pair(begin, point(1, 0)).
pair(point(1, 0), point(1, 1)).
pair(point(1, 1), point(1, 2)).
pair(point(3, 1), point(4, 1)).
pair(point(4, 1), point(5, 1)).
pair(point(5, 1), point(6, 1)).
pair(point(6, 1), point(7, 1)).
pair(point(7, 1), point(7, 2)).
pair(point(1, 2), point(1, 3)).
pair(point(7, 2), point(7, 3)).
pair(point(1, 3), point(1, 4)).
pair(point(1, 3), point(2, 3)).
pair(point(2, 3), point(3, 3)).
pair(point(3, 3), point(4, 3)).
pair(point(4, 3), point(5, 3)).
pair(point(7, 3), point(7, 4)).
pair(point(1, 4), point(1, 5)).
pair(point(7, 4), point(7, 5)).
pair(point(1, 5), point(1, 6)).
pair(finish, point(3, 5)).
pair(point(4, 5), point(5, 5)).
pair(point(4, 5), point(3, 5)).
pair(point(5, 5), point(6, 5)).
pair(point(6, 5), point(7, 5)).
pair(point(7, 5), point(7, 6)).
pair(point(1, 6), point(1, 7)).
pair(point(7, 6), point(7, 7)).
pair(point(1, 7), point(2, 7)).
pair(point(2, 7), point(3, 7)).
pair(point(3, 7), point(4, 7)).
pair(point(4, 7), point(5, 7)).
pair(point(5, 7), point(6, 7)).
pair(point(6, 7), point(7, 7)).