package control;

import java.io.File;
//�������� �ʴ� ���ڵ��϶� �߻��ϴ� ���� 
import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.BoardVO;

public class PicUpload {
	private HttpServletRequest request;

	public PicUpload(HttpServletRequest request) {
		this.request = request;
	}

	public BoardVO upload() {
		BoardVO vo = new BoardVO();
		// Post(���ø����̼� ��İ� ��Ƽ��Ʈ ���)
		// ���� ���ε�� ��Ƽ��Ʈ ���
		if (ServletFileUpload.isMultipartContent(request)) {
			//System.out.println(request);
			// ���� ���ε�� ������ ó���ϱ� ���ؼ� �ʿ��� ��ü
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);

			List<FileItem> fileItemList = null;
			try {
				// ���۵� ���ϰ� �ؽ�Ʈ�� ����(fileItemList)
				fileItemList = fileUpload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < fileItemList.size(); i++) {
				//System.out.println(fileItemList);
				FileItem fileItem = (FileItem) fileItemList.get(i);
				// isFormField()�� �ؽ����� �Ǵ�
				//System.out.println(fileItem);
				if (fileItem.isFormField()) {
					// ����� �̸� �̾Ƽ� ����
					String name = fileItem.getFieldName();
					String value = null;
					try {
						value = fileItem.getString("euc-kr");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (name.equals("title")) {
						vo.setTitle(value);
						System.out.println(vo.getTitle());
					} else if (name.equals("content")) {
						vo.setContent(value);
					} else if (name.equals("section")) {
						vo.setSection(Integer.parseInt(value));
					} else if (name.equals("magazine")) {
						vo.setMagazine(Integer.parseInt(value));
					}

				} else {
					String name = fileItem.getFieldName();
					// ������ ���������� ��ġ (���ϸ� ©�����)
					int idx = fileItem.getName().lastIndexOf("\\");
					// ���࿡ ����(������ �������� ��ġ�� ã�ų� )
					if (idx == -1) {
						idx = fileItem.getName().lastIndexOf("/");
					}
					// ���ϸ� ©�����
					String fileName = fileItem.getName().substring(idx + 1);
					try {

						File uploadedFile = new File("C:\\Users\\bit\\Desktop\\javaWork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\webapps\\img", fileName);
						
						fileItem.write(uploadedFile);
						vo.setPath(uploadedFile.getAbsolutePath());

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return vo;

	}
}
