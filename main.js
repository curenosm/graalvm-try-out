function invertMatrix(matrix) {
    // Get the size of the matrix (assuming it's a square matrix)
    const n = matrix.length;
  
    // Create an identity matrix to store the inverse
    const identityMatrix = Array.from({ length: n }, (_, i) =>
      Array.from({ length: n }, (_, j) => (i === j ? 1 : 0))
    );
  
    // Copy the input matrix to avoid modifying the original matrix
    const copyMatrix = matrix.map(row => [...row]);
  
    // Forward elimination
    for (let i = 0; i < n; i++) {
      // Make the diagonal element 1
      const pivot = copyMatrix[i][i];
      for (let j = 0; j < n; j++) {
        copyMatrix[i][j] /= pivot;
        identityMatrix[i][j] /= pivot;
      }
  
      // Eliminate other elements in the column
      for (let k = 0; k < n; k++) {
        if (k === i) continue;
        const factor = copyMatrix[k][i];
        for (let j = 0; j < n; j++) {
          copyMatrix[k][j] -= factor * copyMatrix[i][j];
          identityMatrix[k][j] -= factor * identityMatrix[i][j];
        }
      }
    }
  
    return identityMatrix;
  }
  
  // Example usage:
  const matrix = [
    [4, 7, 2],
    [2, 6, 8],
    [3, 1, 9]
  ];
  
  const invertedMatrix = invertMatrix(matrix);
  console.log(invertedMatrix);