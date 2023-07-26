import numpy as np


def rotate_matrix_90_clockwise(matrix):
    transposed_matrix = [list(row) for row in zip(*matrix)]
    rotated_matrix = [list(reversed(row)) for row in transposed_matrix]

    return rotated_matrix

matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
]

rotated_matrix = rotate_matrix_90_clockwise(matrix)
print(rotated_matrix)


matrix = np.array([
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
])

print(matrix)