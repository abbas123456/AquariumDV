% 23222222222222222222
% 20000020000000000002
% 20222020222022200202
% 20002020202020000202
% 20222020202020222202
% 20200020202020200202
% 20222220202020200202
% 20000000202020200002
% 20222222202020222222
% 20200000002020210002
% 20222222202022222202
% 20000000002000000002
% 22222222222222222222
%-
path(N,[finish]):-member(finish,N).
path(N,[H2,H|M2]):-setof(X,Y^(member(Y,N),connection(Y,X)),N2),path(N2,[H|M2]),member(H2,N),connection(H2,H).

connection(A,B):- (pair(A,B) ; pair(B,A)).
pair(begin, point(1, 0)).
pair(point(1, 0), point(1, 1)).
pair(point(1, 1), point(1, 2)).
pair(point(1, 1), point(2, 1)).
pair(point(2, 1), point(3, 1)).
pair(point(3, 1), point(4, 1)).
pair(point(4, 1), point(5, 1)).
pair(point(5, 1), point(5, 2)).
pair(point(7, 1), point(7, 2)).
pair(point(7, 1), point(8, 1)).
pair(point(8, 1), point(9, 1)).
pair(point(9, 1), point(10, 1)).
pair(point(10, 1), point(11, 1)).
pair(point(11, 1), point(11, 2)).
pair(point(11, 1), point(12, 1)).
pair(point(12, 1), point(13, 1)).
pair(point(13, 1), point(14, 1)).
pair(point(14, 1), point(15, 1)).
pair(point(15, 1), point(15, 2)).
pair(point(15, 1), point(16, 1)).
pair(point(16, 1), point(16, 2)).
pair(point(16, 1), point(17, 1)).
pair(point(17, 1), point(18, 1)).
pair(point(18, 1), point(18, 2)).
pair(point(1, 2), point(1, 3)).
pair(point(5, 2), point(5, 3)).
pair(point(7, 2), point(7, 3)).
pair(point(11, 2), point(11, 3)).
pair(point(15, 2), point(15, 3)).
pair(point(15, 2), point(16, 2)).
pair(point(16, 2), point(16, 3)).
pair(point(18, 2), point(18, 3)).
pair(point(1, 3), point(1, 4)).
pair(point(1, 3), point(2, 3)).
pair(point(2, 3), point(3, 3)).
pair(point(5, 3), point(5, 4)).
pair(point(7, 3), point(7, 4)).
pair(point(9, 3), point(9, 4)).
pair(point(11, 3), point(11, 4)).
pair(point(13, 3), point(13, 4)).
pair(point(13, 3), point(14, 3)).
pair(point(14, 3), point(15, 3)).
pair(point(15, 3), point(16, 3)).
pair(point(18, 3), point(18, 4)).
pair(point(1, 4), point(1, 5)).
pair(point(5, 4), point(5, 5)).
pair(point(7, 4), point(7, 5)).
pair(point(9, 4), point(9, 5)).
pair(point(11, 4), point(11, 5)).
pair(point(13, 4), point(13, 5)).
pair(point(18, 4), point(18, 5)).
pair(point(1, 5), point(1, 6)).
pair(point(3, 5), point(4, 5)).
pair(point(4, 5), point(5, 5)).
pair(point(7, 5), point(7, 6)).
pair(point(9, 5), point(9, 6)).
pair(point(11, 5), point(11, 6)).
pair(point(13, 5), point(13, 6)).
pair(point(15, 5), point(15, 6)).
pair(point(15, 5), point(16, 5)).
pair(point(16, 5), point(16, 6)).
pair(point(18, 5), point(18, 6)).
pair(point(1, 6), point(1, 7)).
pair(point(7, 6), point(7, 7)).
pair(point(9, 6), point(9, 7)).
pair(point(11, 6), point(11, 7)).
pair(point(13, 6), point(13, 7)).
pair(point(15, 6), point(15, 7)).
pair(point(15, 6), point(16, 6)).
pair(point(16, 6), point(16, 7)).
pair(point(18, 6), point(18, 7)).
pair(point(1, 7), point(1, 8)).
pair(point(1, 7), point(2, 7)).
pair(point(2, 7), point(3, 7)).
pair(point(3, 7), point(4, 7)).
pair(point(4, 7), point(5, 7)).
pair(point(5, 7), point(6, 7)).
pair(point(6, 7), point(7, 7)).
pair(point(9, 7), point(9, 8)).
pair(point(11, 7), point(11, 8)).
pair(point(13, 7), point(13, 8)).
pair(point(15, 7), point(16, 7)).
pair(point(16, 7), point(17, 7)).
pair(point(17, 7), point(18, 7)).
pair(point(1, 8), point(1, 9)).
pair(point(9, 8), point(9, 9)).
pair(point(11, 8), point(11, 9)).
pair(point(13, 8), point(13, 9)).
pair(point(1, 9), point(1, 10)).
pair(point(3, 9), point(4, 9)).
pair(point(4, 9), point(5, 9)).
pair(point(5, 9), point(6, 9)).
pair(point(6, 9), point(7, 9)).
pair(point(7, 9), point(8, 9)).
pair(point(8, 9), point(9, 9)).
pair(point(9, 9), point(9, 10)).
pair(point(11, 9), point(11, 10)).
pair(point(15, 9), point(16, 9)).
pair(finish, point(16, 9)).
pair(point(17, 9), point(18, 9)).
pair(point(17, 9), point(16, 9)).
pair(point(18, 9), point(18, 10)).
pair(point(1, 10), point(1, 11)).
pair(point(9, 10), point(9, 11)).
pair(point(11, 10), point(11, 11)).
pair(point(18, 10), point(18, 11)).
pair(point(1, 11), point(2, 11)).
pair(point(2, 11), point(3, 11)).
pair(point(3, 11), point(4, 11)).
pair(point(4, 11), point(5, 11)).
pair(point(5, 11), point(6, 11)).
pair(point(6, 11), point(7, 11)).
pair(point(7, 11), point(8, 11)).
pair(point(8, 11), point(9, 11)).
pair(point(11, 11), point(12, 11)).
pair(point(12, 11), point(13, 11)).
pair(point(13, 11), point(14, 11)).
pair(point(14, 11), point(15, 11)).
pair(point(15, 11), point(16, 11)).
pair(point(16, 11), point(17, 11)).
pair(point(17, 11), point(18, 11)).